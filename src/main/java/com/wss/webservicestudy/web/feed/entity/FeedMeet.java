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
    }

    public void setStatus(ParticipantStatus status) {
        this.status = status;
    }

    public FeedMeet approve(){
        checkAvailableToApprove();
        setStatus(ParticipantStatus.PARTICIPATING);
        this.feed.addParticipant(this.user);
        return this;
    }

    public FeedMeet cancel() {
        checkAvailableToCancel();
        setStatus(ParticipantStatus.CANCEL);
        this.feed.deductParticipant(this.user);
        return this;
    }

    public FeedMeet refusal(){
        checkAvailableToRefusal();
        setStatus(ParticipantStatus.REFUSAL);
        this.feed.deductParticipant(this.user);
        return this;
    }
    public void checkParticipant(User user){
        if(!isParticipant(user)) {
            throw new IllegalArgumentException("해당 게시글의 참여자가 아닙니다.");
        }
    }
    private void checkAvailableToApprove() {
        if (!isAvailableToApproveStatus()) {
            throw new IllegalArgumentException("승인할 수 없는 상태입니다. 상태 = " + this.status.getName());
        }
    }

    private void checkAvailableToCancel(){
        if (!isAvailableToCancelStatus()) {
            throw new IllegalArgumentException("승인할 수 없는 상태입니다. 상태 = " + this.status.getName());
        }
        if(isWriterSelf()) {
            throw new IllegalArgumentException("게시글의 작성자는 항상 참여상태여야 합니다.");
        }
    }
    private void checkAvailableToRefusal(){
        if (!isAvailableToRefusalStatus()) {
            throw new IllegalArgumentException("승인할 수 없는 상태입니다. 상태 = " + this.status.getName());
        }
        if(isWriterSelf()) {
            throw new IllegalArgumentException("게시글의 작성자는 항상 참여상태여야 합니다.");
        }
    }
    private boolean isParticipant(User user){
        return this.user.getId().equals(user.getId());
    }

    private boolean isAvailableToApproveStatus() {
        return isStatus(ParticipantStatus.APPLYING)
                || isStatus(ParticipantStatus.REFUSAL);
    }

    private boolean isAvailableToCancelStatus(){
        return isStatus(ParticipantStatus.PARTICIPATING);
    }

    private boolean isAvailableToRefusalStatus(){
        return isStatus(ParticipantStatus.PARTICIPATING);
    }
    private boolean isStatus(ParticipantStatus status){
        return this.status.equals(status);
    }
    private boolean isWriterSelf(){
        return user.getId().equals(this.feed.getWriterId());
    }

    @Builder
    public FeedMeet(Feed feed, User user) {
        setFeed(feed);
        setUser(user);
        this.status = ParticipantStatus.APPLYING;
    }
}
