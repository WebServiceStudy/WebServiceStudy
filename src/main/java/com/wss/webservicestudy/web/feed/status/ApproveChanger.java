package com.wss.webservicestudy.web.feed.status;

import com.wss.webservicestudy.web.feed.entity.Feed;
import com.wss.webservicestudy.web.feed.entity.FeedMeet;
import com.wss.webservicestudy.web.feed.service.FeedMeetService;
import com.wss.webservicestudy.web.feed.type.ParticipantStatus;
import com.wss.webservicestudy.web.user.entity.User;
import com.wss.webservicestudy.web.user.service.UserService;

public class ApproveChanger extends ParticipationChanger {

    private final ParticipantStatus status = ParticipantStatus.PARTICIPATING;


    public ApproveChanger(FeedMeetService feedMeetService, UserService userService) {
        super(feedMeetService, userService);
    }

    @Override
    protected void checkChangeAvailable(FeedMeet feedMeet, User currentUser) {
        checkApproveStatus(feedMeet);
        checkFeedWriter(feedMeet, currentUser);
        checkWriterPermission(currentUser);
    }

    @Override
    protected void changeFeedMeetStatus(FeedMeet feedMeet) {
        feedMeet.setStatus(status);
    }

    @Override
    protected void changeParticipantNumber(Feed feed, User actor) {
        feed.addParticipant(actor);
    }

    private void checkApproveStatus(FeedMeet feedMeet) {
        if (!feedMeet.isAvailableToApproveStatus()) {
            throw new IllegalArgumentException("승인할 수 없는 상태입니다. 상태 = " + feedMeet.getStatus().getName());
        }
    }
}
