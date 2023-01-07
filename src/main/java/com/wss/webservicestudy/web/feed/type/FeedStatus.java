package com.wss.webservicestudy.web.feed.type;

public enum FeedStatus {
    RECRUITING("모집중"),
    END("모집 완료");

    private final String name;

    FeedStatus(String name) {
        this.name = name;
    }
}
