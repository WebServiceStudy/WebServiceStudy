package com.wss.webservicestudy.web.feed.entity;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class FeedMeet {

    @Id
    @Column(name = "MEET_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(mappedBy = "feedMeet")
    private long feedId;

    private int max;

    @Column(name = "CUR_MALE")
    private int curMale;

    @Column(name = "CUR_FEMALE")
    private int curFemale;

    // user
}
