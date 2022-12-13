package com.wss.webservicestudy.web.feed.entity;

import com.wss.webservicestudy.web.feed.type.FeedStatus;
import com.wss.webservicestudy.web.user.entity.User;
import lombok.Getter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Entity
public class Feed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID")
    private User writer;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "MEET_ID")
    private FeedMeet feedMeet;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @Lob
    private String content;

    private String lattitude;

    private String longitude;

    private Date date;

    private String addr;

    @Enumerated(EnumType.STRING)
    private FeedStatus status;

}
