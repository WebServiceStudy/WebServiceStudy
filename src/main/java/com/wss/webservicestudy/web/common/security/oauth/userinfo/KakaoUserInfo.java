package com.wss.webservicestudy.web.common.security.oauth.userinfo;

import com.wss.webservicestudy.web.user.entity.User;
import com.wss.webservicestudy.web.user.type.LoginType;
import com.wss.webservicestudy.web.user.type.Role;

import java.util.HashMap;
import java.util.Map;

public class KakaoUserInfo implements OAuthUserInfo {
    private final Map<String, Object> kakao_account;

    public KakaoUserInfo(Map<String, Object> account) {
        this.kakao_account =  (Map<String, Object>) account.get("kakao_account");
    }

    @Override
    public LoginType getLoginType() {
        return LoginType.KAKAO;
    }

    @Override
    public String getName() {
        return (String) ((Map<String, Object>)kakao_account.get("profile")).get("nickname");
    }

    @Override
    public String getEmail() {
        return (String) kakao_account.get("email");
    }

    @Override
    public User toUser() {
        return User.builder()
                .nickname(this.getName())
                .email(this.getEmail())
                .role(Role.USER)
                .loginType(this.getLoginType())
                .build();
    }
}
