package com.wss.webservicestudy.web.feed.status;

import com.wss.webservicestudy.web.feed.entity.Feed;
import com.wss.webservicestudy.web.feed.entity.FeedMeet;
import com.wss.webservicestudy.web.feed.service.FeedMeetService;
import com.wss.webservicestudy.web.feed.type.ParticipantStatus;
import com.wss.webservicestudy.web.user.entity.User;
import com.wss.webservicestudy.web.user.service.UserService;

import java.util.Arrays;
import java.util.List;

public class RefusalChanger extends ParticipationChanger {

    private final ParticipantStatus STATUS = ParticipantStatus.REFUSAL;
    private final List<ParticipantStatus> PRE_STATUS = Arrays.asList(ParticipantStatus.APPLYING, ParticipantStatus.PARTICIPATING);

    public RefusalChanger(FeedMeetService feedMeetService, UserService userService) {
        super(feedMeetService, userService);
    }

    @Override
    protected ParticipantStatus getNewStatus() {
        return STATUS;
    }

    @Override
    protected List<ParticipantStatus> getPreStatusList() {
        return PRE_STATUS;
    }

    @Override
    protected void checkChangeAvailable(FeedMeet feedMeet, User currentUser) {
        checkStatus(feedMeet);
        checkWriterPermission(currentUser);
        checkFeedWriter(feedMeet, currentUser);
        checkIsWriterSelf(feedMeet);
    }

    @Override
    protected void changeParticipantNumber(FeedMeet feedMeet, Feed feed, User actor) {
        if (feedMeet.isParticipating()) {
            feed.deductParticipant(actor);
        }
    }
}
