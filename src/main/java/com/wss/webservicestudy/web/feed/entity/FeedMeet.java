package com.wss.webservicestudy.web.feed.entity;

import com.wss.webservicestudy.web.user.entity.User;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class FeedMeet {

    @Id
    @Column(name = "MEET_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FEED_ID")
    private Feed feed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID")
    private User user;


}
