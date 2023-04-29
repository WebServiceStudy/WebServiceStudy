package com.wss.webservicestudy.web.user.service;

import com.wss.webservicestudy.web.common.entity.token.RefreshToken;
import com.wss.webservicestudy.web.common.entity.token.RefreshTokenRepository;
import com.wss.webservicestudy.web.common.exception.BizException;
import com.wss.webservicestudy.web.common.security.jwt.dto.TokenRequestDto;
import com.wss.webservicestudy.web.common.security.jwt.JwtTokenProvider;
import com.wss.webservicestudy.web.common.security.jwt.dto.TokenInfo;
import com.wss.webservicestudy.web.common.util.SecurityUtil;
import com.wss.webservicestudy.web.common.util.StringUtil;
import com.wss.webservicestudy.web.user.dto.SignUpReqDto;
import com.wss.webservicestudy.web.user.dto.UserLoginReqDto;
import com.wss.webservicestudy.web.user.dto.UserRequestDto;
import com.wss.webservicestudy.web.user.dto.UserRespDto;
import com.wss.webservicestudy.web.user.entity.User;
import com.wss.webservicestudy.web.user.exception.AlreadyExistUserException;
import com.wss.webservicestudy.web.user.exception.RequireLoginExceptrion;
import com.wss.webservicestudy.web.user.repository.UserRepository;
import com.wss.webservicestudy.web.user.type.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;


@Slf4j
@Service
public class UserService {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${image.save.path}")
    private String imageSavePath;

    @Value("${image.server.path}")
    private String imageServerPath;

    final long refreshTokenValidTime = 1000L * 60 * 60 * 24 * 30;

    public UserService(AuthenticationManagerBuilder authenticationManagerBuilder, UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider tokenProvider, RefreshTokenRepository refreshTokenRepository) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Transactional
    public UserRespDto signup(SignUpReqDto reqDto) {
        if (userRepository.existsByEmail(reqDto.getEmail())) {
            throw AlreadyExistUserException.getInstance();
        }

        UserRespDto result = null;
        if (validSignUp(reqDto)) {
            User user = reqDto.toUser(passwordEncoder);
            result = userRepository.save(user).toDto();
        }
        return result;
    }

    private boolean validSignUp(SignUpReqDto reqDto) {
        if (!StringUtil.isValidEmail(reqDto.getEmail())) {
            throw new BizException("올바르지 않은 이메일 형식입니다.");
        }
        if (!StringUtil.validPwd(reqDto.getPassword())) {
            throw new BizException("패스워드는 영문, 숫자, 특수문자 조합 8자리 이상으로 구성되어야 합니다.");
        }
        return true;
    }

    @Transactional
    public TokenInfo login(UserLoginReqDto reqDto, HttpServletResponse response) {
        // 1. Login ID/PW 를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = reqDto.toAuthentication();

        // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        //    authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        log.info(authentication.getAuthorities().toString());

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenInfo tokenDto = tokenProvider.createToken(authentication);
        ResponseCookie cookie = ResponseCookie.from("rf", tokenDto.getRefreshToken())
                .domain("localhost")
                .sameSite("Lax")
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(refreshTokenValidTime)
                .build();
        response.addHeader("Set-Cookie", cookie.toString());
        // 4. RefreshToken 저장
        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        // 5. 토큰 발급
        return tokenDto;
    }

    @Transactional
    public TokenInfo reissue(TokenRequestDto tokenRequestDto, HttpServletRequest request, HttpServletResponse response) {
        Cookie refreshTokenCookie = Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals("rf"))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN, "로그인이 필요합니다."));

        tokenRequestDto.setRefreshToken(refreshTokenCookie.getValue());

        // 1. Refresh Token 검증
        if (!tokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "로그인이 필요합니다.");
        }

        // 2. Access Token 에서 Member ID 가져오기
        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

        log.info(authentication.getName());
        // 3. 저장소에서 Member ID 를 기반으로 Refresh Token 값 가져옴
        RefreshToken rfToken = refreshTokenRepository.findById(authentication.getName())
                .orElseThrow(() -> new RuntimeException("로그인이 필요한 사용자입니다"));

        // 4. Refresh Token 일치하는지 검사
        if (!rfToken.getValue().equals(tokenRequestDto.getRefreshToken())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "로그인이 필요합니다.");
        }

        // 5. 새로운 토큰 생성
        TokenInfo tokenDto = tokenProvider.createToken(authentication);

        // 6. 저장소 정보 업데이트
        RefreshToken newRefreshToken = rfToken.updateValue(tokenDto.getRefreshToken());
        refreshTokenRepository.save(newRefreshToken);

        ResponseCookie cookie = ResponseCookie.from("rf", tokenDto.getRefreshToken())
                .domain("localhost")
                .sameSite("Lax")
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(refreshTokenValidTime)
                .build();
        response.addHeader("Set-Cookie", cookie.toString());

        // 토큰 발급
        return tokenDto;
    }


    public UserRespDto getUserInfo(String email){
//        User user = userRepository.findByEmail(email);
//        return user.toDto();
        //test중
        User user = SecurityUtil.getLoginUser();
        return user.toDto();
    }

    public User findCurrentUser(){
        User user = userRepository.findByEmail(SecurityUtil.getCurrentMember());
        if (user == null) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다");
        }
        return user;
    }

    public Long findUserIdByEmail(String email){
        return userRepository.findByEmail(email).getId();
    }

    /**
     * 유저 정보 업데이트
     */
    public UserRespDto updateInfo(UserRequestDto req) {
        UserRespDto response = null;

        User updateUser = userRepository.findByEmail(req.getEmail());

        if (updateUser == null) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다");
        }

        updateUser = req.toUser();

        response = userRepository.save(updateUser).toDto();

        return response;
    }


    public UserRespDto updateProfile(MultipartFile uploadImg) throws IOException {
        UserRespDto response = null;

        User user = findCurrentUser();

        String fileName = uploadImg.getOriginalFilename();
        String extenstion = StringUtils.getFilenameExtension(fileName);
        String imgName = "img_"+ UUID.randomUUID()+"."+extenstion;

        String saveFilePath = imageSavePath + imgName;

        File directory = new File(imageSavePath);
        if (!directory.exists()){
            directory.mkdirs();
        }
        // 이미지 파일 저장
        File saveFile = new File(saveFilePath);

        uploadImg.transferTo(saveFile);

        user.setProfile(imageServerPath + "/img/" + imgName);

        response = userRepository.save(user).toDto();

        return response;
    }
}
