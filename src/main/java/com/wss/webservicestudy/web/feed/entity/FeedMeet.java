package com.wss.webservicestudy.web.feed.entity;

import com.wss.webservicestudy.web.common.entity.BaseEntity;
import com.wss.webservicestudy.web.feed.type.ParticipantStatus;
import com.wss.webservicestudy.web.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Table(indexes = @Index(name="unique_idx_feedmeet_feed_user", columnList = "FEED_ID, USER_ID", unique = true))
public class FeedMeet extends BaseEntity {
    // 식별값
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 피드
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FEED_ID", referencedColumnName = "ID")
    private Feed feed;

    // 참여자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private User user;

    @Enumerated(EnumType.STRING)
    private ParticipantStatus status;

    public void setFeed(Feed feed) {
        this.feed = feed;
        feed.getFeedMeets().add(this);
    }

    public void setUser(User user){
        this.user = user;
        user.getFeedMeets().add(this);
    }

    public FeedMeet approveByWriter(User writer) {
        Feed feed = this.getFeed();
        feed.checkWriter(writer.getId());
        this.status = ParticipantStatus.PARTICIPATING;
        feed.addParticipant(this.user);
        return this;
    }

    public FeedMeet cancelByParticipant(User participant) {
        checkParticipant(participant);
        this.status = ParticipantStatus.CANCEL;
        return this;
    }

    public FeedMeet refusalByWriter(User writer){
        this.getFeed().checkWriter(writer.getId());
        this.status = ParticipantStatus.REFUSAL;
        return this;
    }

    public boolean checkParticipant(User user){
        if(!isParticipant(user)) {
            throw new IllegalArgumentException("해당 게시글의 참여자가 아닙니다.");
        }
        return true;
    }
    private boolean isParticipant(User user){
        return this.user.getId().equals(user.getId());
    }

    @Builder
    public FeedMeet(Feed feed, User user) {
        setFeed(feed);
        setUser(user);
        this.status = ParticipantStatus.APPLYING;
    }
}
