package com.wss.webservicestudy.web.feed.entity;

import com.wss.webservicestudy.web.feed.type.ParticipantStatus;
import com.wss.webservicestudy.web.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Table(indexes = @Index(name="unique_idx_feedmeet_feed_user", columnList = "feed_id, user_id", unique = true))
public class FeedMeet {
    // 식별값
    @Id
    @Column(name = "MEET_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 피드
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FEED_ID", referencedColumnName = "FEED_ID")
    private Feed feed;

    // 참여자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Enumerated(EnumType.STRING)
    private ParticipantStatus status;

//    public static FeedMeet createFeedMeet(Feed feed) {
//        FeedMeet feedMeet = new FeedMeet();
//        feedMeet.setFeed(feed);
//        feed.addFeedMeet(feedMeet);
//        return feedMeet;
//    }

    public void setFeed(Feed feed) {
        this.feed = feed;
        feed.getFeedMeets().add(this);
    }

    public void setUser(User user){
        this.user = user;
        user.getFeedMeets().add(this);
    }

    public FeedMeet approve() {
        this.status = ParticipantStatus.PARTICIPATING;
        return this;
    }

    @Builder
    public FeedMeet(Feed feed, User user) {
        setFeed(feed);
        setUser(user);
        this.status = ParticipantStatus.APPLYING;
    }

}
