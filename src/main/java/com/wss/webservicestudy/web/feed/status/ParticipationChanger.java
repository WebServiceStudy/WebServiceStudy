package com.wss.webservicestudy.web.feed.status;

import com.wss.webservicestudy.web.feed.entity.Feed;
import com.wss.webservicestudy.web.feed.entity.FeedMeet;
import com.wss.webservicestudy.web.feed.service.FeedMeetService;
import com.wss.webservicestudy.web.feed.type.ParticipantStatus;
import com.wss.webservicestudy.web.user.entity.User;
import com.wss.webservicestudy.web.user.service.UserService;

import java.util.List;

public abstract class ParticipationChanger {

    private final FeedMeet feedMeet;
    private final UserService userService;

    protected ParticipationChanger(FeedMeet feedMeet, UserService userService) {
        this.feedMeet = feedMeet;
        this.userService = userService;
    }

    public FeedMeet changeStatus() {
        // 상태 변경 가능 여부 체크
        checkChangeAvailable(userService.findCurrentUser());
        updateFeedMeet();
        return this.feedMeet;
    }

    private final void updateFeedMeet() {
        changeParticipantNumber(this.feedMeet.getFeed(), this.feedMeet.getUser());
        changeFeedMeetStatus();
    }

    protected final void changeFeedMeetStatus() {
        this.feedMeet.setStatus(getNewStatus());
    }

    protected final void checkStatus() {
        if (!getPreStatusList().contains(this.feedMeet.getStatus())) {
            throw new IllegalArgumentException("승인할 수 없는 상태입니다. 상태 = " + this.feedMeet.getStatus().getName());
        }
    }

    protected final void checkWriterPermission(User actor) {
        if(!actor.isWritable()){
            throw new IllegalArgumentException("쓰기 권한 없음");
        }
    }

    protected final void checkFeedWriter(User actor) {
        if (!this.feedMeet.isFeedWriter(actor)) {
            throw new IllegalArgumentException("해당 게시글의 작성자가 아닙니다.");
        }
    }

    protected final void checkIsWriterSelf() {
        if(this.feedMeet.isWriterSelf()) {
            throw new IllegalArgumentException("게시글의 작성자는 항상 참여상태여야 합니다.");
        }
    }

    protected final FeedMeet getFeedMeet() {
        return this.feedMeet;
    }

    protected abstract void checkChangeAvailable(User actor);

    protected abstract void changeParticipantNumber(Feed feed, User user);

    protected abstract ParticipantStatus getNewStatus();

    protected abstract List<ParticipantStatus> getPreStatusList();
}
