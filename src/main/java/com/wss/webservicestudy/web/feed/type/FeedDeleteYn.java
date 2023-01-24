package com.wss.webservicestudy.web.feed.type;

public enum FeedDeleteYn {
    DELETED("Y"),
    UNDELETED("N");

    private final String name;

    FeedDeleteYn(String name) {
        this.name = name;
    }
}
