package com.wss.webservicestudy.web.common.security.domain;

import com.wss.webservicestudy.web.user.entity.User;
import lombok.Getter;

@Getter
public class SessionUser {
    private String name;
    private String email;

    public SessionUser(User user) {
        this.name = user.getNickname();
        this.email = user.getEmail();
    }
}
