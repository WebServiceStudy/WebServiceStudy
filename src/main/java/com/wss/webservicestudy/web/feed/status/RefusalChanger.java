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

    public RefusalChanger(FeedMeet feedMeet, UserService userService) {
        super(feedMeet, userService);
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
    protected void checkChangeAvailable(User actor) {
        checkStatus();
        checkWriterPermission(actor);
        checkFeedWriter(actor);
        checkIsWriterSelf();
    }

    @Override
    protected void changeParticipantNumber(Feed feed, User user) {
        if (getFeedMeet().isParticipating()) {
            feed.deductParticipant(user);
        }
    }
}
