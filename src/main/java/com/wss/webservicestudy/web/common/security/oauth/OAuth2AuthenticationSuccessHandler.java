package com.wss.webservicestudy.web.common.security.oauth;

import com.wss.webservicestudy.web.common.constant.Constants;
import com.wss.webservicestudy.web.common.entity.token.RefreshToken;
import com.wss.webservicestudy.web.common.entity.token.RefreshTokenRepository;
import com.wss.webservicestudy.web.common.security.domain.PrincipalDetail;
import com.wss.webservicestudy.web.common.security.jwt.JwtTokenProvider;
import com.wss.webservicestudy.web.common.security.jwt.dto.TokenInfo;
import com.wss.webservicestudy.web.user.dto.UserRespDto;
import com.wss.webservicestudy.web.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    JwtTokenProvider jwtTokenUtil;
//    UserService userService;

    RefreshTokenRepository refreshTokenRepository;


    public OAuth2AuthenticationSuccessHandler(JwtTokenProvider jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        PrincipalDetail principalDetails = (PrincipalDetail) authentication.getPrincipal();
        UserRespDto userRespDto = principalDetails.getUser().toDto();

        TokenInfo createToken = jwtTokenUtil.createToken(authentication);

        RefreshToken rfToken = RefreshToken.builder()
                .key(userRespDto.getEmail())
                .value(createToken.getRefreshToken())
                .build();

        refreshTokenRepository.save(rfToken);

        String url = makeRedirectUrl(createToken.getAccessToken());
        getRedirectStrategy().sendRedirect(request, response, url);


    }

    //
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//                                        Authentication authentication) throws IOException {
//        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
//        UserResponse userResponse = UserResponse.from(principalDetails.getUser());
//
//        String createdAccessToken = jwtTokenUtil.createAccessToken(userResponse);
//        String refreshToken = jwtTokenUtil.createRefreshToken();
//
//        AccessToken accessToken = AccessToken.builder()
//                .email(userResponse.getEmail())
//                .accessToken(createdAccessToken)
//                .build();
//
//        userCommandService.saveAccessToken(accessToken);
//        userCommandService.updateRefreshTokenByUserId(userResponse.getEmail(), refreshToken);
//        String url = makeRedirectUrl(createdAccessToken);
//        getRedirectStrategy().sendRedirect(request, response, url);
//
//    }
    private String makeRedirectUrl(String token) {
        return UriComponentsBuilder.fromUriString(Constants.FRONT_URL+"/oauth2/redirect/"+token)
                .build().toUriString();
    }
}