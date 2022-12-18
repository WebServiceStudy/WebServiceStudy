package com.wss.webservicestudy.web.feed.entity;

import com.wss.webservicestudy.web.feed.type.FeedStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Feed {

    @Id
    @Column(name = "FEED_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID")
    private User writer;*/

    @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL)
    private List<FeedMeet> feedMeets = new ArrayList<>();

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @Lob
    private String content;

    @Enumerated(EnumType.STRING)
    private FeedStatus status;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private String addr;

    private String latitude;
    private String longitude;

    private int maxUser;
    private int curMale;
    private int curFemale;

    public void addFeedMeet(FeedMeet feedMeet) {
        this.feedMeets.add(feedMeet);
        feedMeet.setFeed(this);
    }
}
