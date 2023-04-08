package com.wss.webservicestudy.web.feed.status;

import com.wss.webservicestudy.web.feed.entity.Feed;
import com.wss.webservicestudy.web.feed.entity.FeedMeet;
import com.wss.webservicestudy.web.feed.service.FeedMeetService;
import com.wss.webservicestudy.web.feed.type.ParticipantStatus;
import com.wss.webservicestudy.web.user.entity.User;
import com.wss.webservicestudy.web.user.service.UserService;

public class RefusalChanger extends ParticipationChanger {

    private final ParticipantStatus status = ParticipantStatus.PARTICIPATING;


    public RefusalChanger(FeedMeetService feedMeetService, UserService userService) {
        super(feedMeetService, userService);
    }

    @Override
    protected void checkChangeAvailable(FeedMeet feedMeet, User currentUser) {
        checkWriterPermission(currentUser);
        checkFeedWriter(feedMeet, currentUser);
        checkIsWriterSelf(feedMeet);
        checkRefusalStatus(feedMeet);
    }

    @Override
    protected void changeFeedMeetStatus(FeedMeet feedMeet) {
        feedMeet.setStatus(status);
    }

    @Override
    protected void changeParticipantNumber(Feed feed, User actor) {
        feed.addParticipant(actor);
    }

    private static void checkRefusalStatus(FeedMeet feedMeet) {
        if (!feedMeet.isAvailableToRefusalStatus()) {
            throw new IllegalArgumentException("거절할 수 없는 상태입니다. 상태 = " + feedMeet.getStatus().getName());
        }
    }
}
