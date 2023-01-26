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
        feed.checkWriter(writer);
        feed.availableToAdd();
        availableToApprove();

        this.status = ParticipantStatus.PARTICIPATING;
        this.feed.addParticipant(this.user);
        return this;
    }

    public FeedMeet cancelByParticipant(User participant) {
        checkParticipant(participant);
        this.status = ParticipantStatus.CANCEL;
        deductParticipant(participant);
        return this;
    }

    public FeedMeet refusalByWriter(User writer){
        this.getFeed().checkWriter(writer);
        checkWriterSelfCancel(writer);
        this.status = ParticipantStatus.REFUSAL;
        deductParticipant(this.user);
        return this;
    }

    public boolean checkParticipant(User user){
        if(!isParticipant(user)) {
            throw new IllegalArgumentException("해당 게시글의 참여자가 아닙니다.");
        }
        return checkWriterSelfCancel(user);
    }

    private boolean isParticipant(User user){
        return this.user.getId().equals(user.getId());
    }

    public boolean checkWriterSelfCancel(User user) {
        if(user.getId().equals(this.feed.getWriterId())) {
            throw new IllegalArgumentException("게시글의 작성자는 항상 참여상태여야 합니다.");
        }
        return true;
    }

    private void deductParticipant(User user) {
        if (this.status.equals(ParticipantStatus.PARTICIPATING)) {
            this.feed.deductParticipant(user);
        }
    }

    private boolean availableToApprove() {
        if (this.status.equals(ParticipantStatus.APPLYING) || this.status.equals(ParticipantStatus.REFUSAL)) {
            return true;
        }
        throw new IllegalArgumentException("승인할 수 없는 상태입니다. 상태 = " + this.status.getName());
    }

    @Builder
    public FeedMeet(Feed feed, User user) {
        setFeed(feed);
        setUser(user);
        this.status = ParticipantStatus.APPLYING;
    }
}
