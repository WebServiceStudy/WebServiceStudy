package com.wss.webservicestudy.web.feed.status;

import com.wss.webservicestudy.web.feed.entity.Feed;
import com.wss.webservicestudy.web.feed.entity.FeedMeet;
import com.wss.webservicestudy.web.feed.service.FeedMeetService;
import com.wss.webservicestudy.web.feed.type.ParticipantStatus;
import com.wss.webservicestudy.web.user.entity.User;
import com.wss.webservicestudy.web.user.service.UserService;

import java.util.Arrays;
import java.util.List;

public class ApproveChanger extends ParticipationChanger {

    private final ParticipantStatus status = ParticipantStatus.PARTICIPATING;
    private final List<ParticipantStatus> preStatus = Arrays.asList(ParticipantStatus.APPLYING, ParticipantStatus.REFUSAL);

    public ApproveChanger(FeedMeetService feedMeetService, UserService userService) {
        super(feedMeetService, userService);
    }

    @Override
    protected void checkChangeAvailable(FeedMeet feedMeet, User currentUser) {
        checkStatus(feedMeet);
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

    @Override
    protected void checkStatus(FeedMeet feedMeet) {
        if (!preStatus.contains(feedMeet.getStatus())) {
            throw new IllegalArgumentException("승인할 수 없는 상태입니다. 상태 = " + feedMeet.getStatus().getName());
        }
    }
}
