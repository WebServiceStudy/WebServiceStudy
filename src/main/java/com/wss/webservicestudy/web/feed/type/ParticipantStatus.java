package com.wss.webservicestudy.web.feed.type;

public enum ParticipantStatus {

    PARTICIPATING("참여중"),
    APPLYING("참여 요청");

    private final String name;

    ParticipantStatus(String name) {
        this.name = name;
    }
}
