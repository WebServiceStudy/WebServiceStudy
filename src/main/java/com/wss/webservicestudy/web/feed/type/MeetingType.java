package com.wss.webservicestudy.web.feed.type;

public enum MeetingType {
    NO_DISTINCTION("구분없음"),
    GENDER_DIVISION("성별구분");

    private final String name;

    MeetingType(String name) {
        this.name = name;
    }
}
