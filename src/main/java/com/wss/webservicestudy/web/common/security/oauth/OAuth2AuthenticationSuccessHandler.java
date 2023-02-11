package com.wss.webservicestudy.web.common.security.oauth;

import com.wss.webservicestudy.web.common.constant.Constants;
import com.wss.webservicestudy.web.common.entity.token.RefreshToken;
import com.wss.webservicestudy.web.common.entity.token.RefreshTokenRepository;
import com.wss.webservicestudy.web.common.security.domain.PrincipalDetail;
import com.wss.webservicestudy.web.common.security.jwt.JwtTokenProvider;
import com.wss.webservicestudy.web.common.security.jwt.dto.TokenInfo;
import com.wss.webservicestudy.web.user.dto.UserRespDto;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

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

    final long refreshTokenValidTime = 1000L * 60 * 60 * 24 * 30;

    public OAuth2AuthenticationSuccessHandler(JwtTokenProvider jwtTokenUtil, RefreshTokenRepository refreshTokenRepository) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.refreshTokenRepository = refreshTokenRepository;
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

        ResponseCookie cookie = ResponseCookie.from("rf", createToken.getRefreshToken())
                .domain("localhost")
                .sameSite("Lax")
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(refreshTokenValidTime)
                .build();
        response.addHeader("Set-Cookie", cookie.toString());

        refreshTokenRepository.save(rfToken);

        response.sendRedirect(Constants.FRONT_URL+"/user/oauth2/redirect?ac="+createToken.getAccessToken());
    }
}