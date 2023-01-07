package com.wss.webservicestudy.web.user.type;

public enum Gender {
    MALE("남자"),
    FEMALE("여자");

    private final String name;
    Gender(String name) {
        this.name = name;
    }
}
