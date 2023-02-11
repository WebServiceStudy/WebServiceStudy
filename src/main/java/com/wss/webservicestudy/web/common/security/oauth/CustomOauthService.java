package com.wss.webservicestudy.web.common.security.oauth;

import com.wss.webservicestudy.web.common.security.domain.PrincipalDetail;
import com.wss.webservicestudy.web.common.security.oauth.userinfo.GoogleUserInfo;
import com.wss.webservicestudy.web.common.security.domain.SessionUser;
import com.wss.webservicestudy.web.common.security.oauth.userinfo.KakaoUserInfo;
import com.wss.webservicestudy.web.common.security.oauth.userinfo.OAuthUserInfo;
import com.wss.webservicestudy.web.user.entity.User;
import com.wss.webservicestudy.web.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Map;

@Slf4j
@Service
public class CustomOauthService extends DefaultOAuth2UserService {

    private UserRepository userRepository;
    private HttpSession httpSession;
    private PasswordEncoder passwordEncoder;

    @Value("${oauth.default.pwd}")
    private String pwd;

    public CustomOauthService(UserRepository userRepository, HttpSession httpSession, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.httpSession = httpSession;
        this.passwordEncoder= passwordEncoder;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
        Map<String, Object> attributes = oAuth2User.getAttributes();
        String provider = oAuth2UserRequest.getClientRegistration().getRegistrationId();
        OAuthUserInfo oauthUserInfo = getOauthUserInfo(provider, attributes);

        if (oauthUserInfo == null) throw new AssertionError();

        User user = getUserEntityByOauthUserInfo(oauthUserInfo);

        return new PrincipalDetail(user, attributes);
    }

    private OAuthUserInfo getOauthUserInfo(String provider, Map<String, Object> attributes) {
        if(provider.equals("kakao")){
            return new KakaoUserInfo(attributes);
        }
        if(provider.equals("google")){
            return new GoogleUserInfo(attributes);
        }
        return null;
    }

    private User getUserEntityByOauthUserInfo(OAuthUserInfo oauth2UserInfo) {
        String email = oauth2UserInfo.getEmail();

        if (!userRepository.existsByEmail(email)) {
            User newUser = oauth2UserInfo.toUser();
            newUser.setPassword(passwordEncoder.encode(pwd));
            return userRepository.save(newUser);
        }
        return userRepository.findByEmail(email);
    }
}

