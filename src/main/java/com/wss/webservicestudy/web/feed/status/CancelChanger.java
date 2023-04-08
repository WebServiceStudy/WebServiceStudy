package com.wss.webservicestudy.web.feed.status;

import com.wss.webservicestudy.web.feed.entity.Feed;
import com.wss.webservicestudy.web.feed.entity.FeedMeet;
import com.wss.webservicestudy.web.feed.service.FeedMeetService;
import com.wss.webservicestudy.web.feed.type.ParticipantStatus;
import com.wss.webservicestudy.web.user.entity.User;
import com.wss.webservicestudy.web.user.service.UserService;

public class CancelChanger extends ParticipationChanger {

    private final ParticipantStatus status = ParticipantStatus.CANCEL;


    public CancelChanger(FeedMeetService feedMeetService, UserService userService) {
        super(feedMeetService, userService);
    }

    @Override
    protected void checkChangeAvailable(FeedMeet feedMeet, User currentUser) {
        checkParticipant(feedMeet, currentUser);
        checkIsWriterSelf(feedMeet);
        checkCancelStatus(feedMeet);
    }

    @Override
    protected void changeFeedMeetStatus(FeedMeet feedMeet) {
        feedMeet.setStatus(status);
    }

    @Override
    protected void changeParticipantNumber(Feed feed, User actor) {
        feed.deductParticipant(actor);
    }

    private void checkParticipant(FeedMeet feedMeet, User currentUser) {
        if(!feedMeet.equalsParticipant(currentUser)) {
            throw new IllegalArgumentException("본인의 신청내역만 취소가 가능합니다.");
        }
    }

    private void checkCancelStatus(FeedMeet feedMeet) {
        if (!feedMeet.isAvailableToCancelStatus()) {
            throw new IllegalArgumentException("취소할 수 없는 상태입니다. 상태 = " + feedMeet.getStatus().getName());
        }
    }
}
