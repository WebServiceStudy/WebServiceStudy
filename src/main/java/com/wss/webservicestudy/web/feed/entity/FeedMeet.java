package com.wss.webservicestudy.web.feed.entity;

import com.wss.webservicestudy.web.user.entity.User;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class FeedMeet {
    // 식별값
    @Id
    @Column(name = "MEET_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // 피드
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FEED_ID")
    private Feed feed;

    // 참여자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID")
    private User user;


}
