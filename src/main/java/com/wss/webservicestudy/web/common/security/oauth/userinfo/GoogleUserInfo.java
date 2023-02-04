package com.wss.webservicestudy.web.common.security.oauth.userinfo;

import com.wss.webservicestudy.web.user.entity.User;
import com.wss.webservicestudy.web.user.type.LoginType;
import com.wss.webservicestudy.web.user.type.Role;

import java.util.Map;

public class GoogleUserInfo implements OAuthUserInfo {

    private final Map<String, Object> attributes;

    public GoogleUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public LoginType getLoginType() {
        return LoginType.GOOGLE;
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
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
