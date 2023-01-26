package com.wss.webservicestudy.web.feed.type;

public enum ParticipantStatus {

    PARTICIPATING("참여중"),
    APPLYING("참여 요청"),
    CANCEL("참여 취소"),
    REFUSAL("작성자 거절");

    private final String name;

    ParticipantStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
