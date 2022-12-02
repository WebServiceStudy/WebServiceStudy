package com.wss.webservicestudy.web.user.type;

public enum LoginType {
    KAKAO("카카오"),
    GOOGLE("구글")
    ;

    private final String name;
    LoginType(String name) {
        this.name = name;
    }
}
