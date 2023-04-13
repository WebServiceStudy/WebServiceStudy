package com.wss.webservicestudy.web.feed.status;

import com.wss.webservicestudy.web.feed.entity.Feed;
import com.wss.webservicestudy.web.feed.entity.FeedMeet;
import com.wss.webservicestudy.web.feed.service.FeedMeetService;
import com.wss.webservicestudy.web.feed.type.ParticipantStatus;
import com.wss.webservicestudy.web.user.entity.User;
import com.wss.webservicestudy.web.user.service.UserService;

import java.util.List;

public abstract class ParticipationChanger {

    private final FeedMeetService feedMeetService;
    private final UserService userService;

    protected ParticipationChanger(FeedMeetService feedMeetService, UserService userService) {
        this.feedMeetService = feedMeetService;
        this.userService = userService;
    }

    public FeedMeet changeStatus(long feedMeetId) {
        FeedMeet feedMeet = feedMeetService.read(feedMeetId);
        checkChangeAvailable(feedMeet, userService.findCurrentUser());
        updateFeedMeet(feedMeet);
        return feedMeet;
    }

    private void updateFeedMeet(FeedMeet feedMeet) {
        changeFeedMeetStatus(feedMeet);
        changeParticipantNumber(feedMeet.getFeed(), feedMeet.getUser());
    }

    protected abstract ParticipantStatus getNewStatus();

    protected abstract List<ParticipantStatus> getPreStatusList();

    protected void checkChangeAvailable(FeedMeet feedMeet, User currentUser) {}

    protected void changeFeedMeetStatus(FeedMeet feedMeet) {
        feedMeet.setStatus(getNewStatus());
    }

    protected void changeParticipantNumber(Feed feed, User actor) {}

    protected void checkStatus(FeedMeet feedMeet) {
        if (!getPreStatusList().contains(feedMeet.getStatus())) {
            throw new IllegalArgumentException("승인할 수 없는 상태입니다. 상태 = " + feedMeet.getStatus().getName());
        }
    }

    protected void checkWriterPermission(User currentUser) {
        if(!currentUser.isWritable()){
            throw new IllegalArgumentException("쓰기 권한 없음");
        }
    }

    protected void checkFeedWriter(FeedMeet feedMeet, User currentUser) {
        if (!feedMeet.isFeedWriter(currentUser)) {
            throw new IllegalArgumentException("해당 게시글의 작성자가 아닙니다.");
        }
    }

    protected void checkIsWriterSelf(FeedMeet feedMeet) {
        if(feedMeet.isWriterSelf()) {
            throw new IllegalArgumentException("게시글의 작성자는 항상 참여상태여야 합니다.");
        }
    }
}
