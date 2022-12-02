package com.wss.webservicestudy.web.common.security.oauth.userinfo;


import com.wss.webservicestudy.web.user.entity.User;
import com.wss.webservicestudy.web.user.type.LoginType;

public interface OAuthUserInfo {


    LoginType getLoginType();
    String getName();
    String getEmail();

    User toUser();
}
