package com.wss.webservicestudy.web.common.security.core.userdetails;

import com.wss.webservicestudy.web.user.domain.User_jieun;
import com.wss.webservicestudy.web.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.UUID;


public class CustomOAuthUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomOAuthUserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // /oauth2/authorization/google
//    @Override
//    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//        OAuth2User oAuth2User = super.loadUser(userRequest);
//
//        String provider = userRequest.getClientRegistration().getRegistrationId();    //google
//        String providerId = oAuth2User.getAttribute("sub");
//        String username = provider+"_"+providerId;  			// 사용자가 입력한 적은 없지만 만들어준다
//
//        String uuid = UUID.randomUUID().toString().substring(0, 6);
//        String password = passwordEncoder.encode(uuid);  // 사용자가 입력한 적은 없지만 만들어준다
//
//        String email = oAuth2User.getAttribute("email");
//
//        User_jieun user = userRepository.findByUsername(username);
//
//        //DB에 없는 사용자라면 회원가입처리
//        if(user == null){
//            user = User_jieun.oauth2Register()
//                    .username(username).password(password).email(email)
//                    .provider(provider).providerId(providerId)
//                    .build();
//            userRepository.save(user);
//        }
//
//        return new PrincipalDetails(user, oAuth2User.getAttributes());
//    }
}
