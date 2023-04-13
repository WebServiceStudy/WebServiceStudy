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
    protected ParticipantStatus getNewStatus() {
        return status;
    }

    @Override
    protected List<ParticipantStatus> getPreStatusList() {
        return preStatus;
    }

    @Override
    protected void checkChangeAvailable(FeedMeet feedMeet, User currentUser) {
        checkStatus(feedMeet);
        checkFeedWriter(feedMeet, currentUser);
        checkWriterPermission(currentUser);
    }

    @Override
    protected void changeParticipantNumber(Feed feed, User actor) {
        feed.addParticipant(actor);
    }
}
