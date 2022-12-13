package com.wss.webservicestudy.web.common.security.oauth.userinfo;

import com.wss.webservicestudy.web.user.entity.User;
import com.wss.webservicestudy.web.user.type.LoginType;
import com.wss.webservicestudy.web.user.type.Role;

import java.util.Map;

public class GoogleUserInfo implements OAuthUserInfo{
    private final Map<String, Object> google_account;
    public GoogleUserInfo(Map<String, Object> account) {
        this.google_account = account; //json으로 준다
    }

    @Override
    public LoginType getLoginType() {
        return LoginType.GOOGLE;
    }

    @Override
    public String getName() {
        return (String) google_account.get("name");
    }

    @Override
    public String getEmail() {
        return (String) google_account.get("email");
    }

    @Override
    public User toUser() {
        return User.builder()
                .name(this.getName())
                .email(this.getEmail())
                .role(Role.USER)
                .loginType(this.getLoginType())
                .build();
    }
}
