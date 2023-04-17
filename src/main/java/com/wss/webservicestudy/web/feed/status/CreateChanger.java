package com.wss.webservicestudy.web.feed.status;

import com.wss.webservicestudy.web.feed.entity.Feed;
import com.wss.webservicestudy.web.feed.entity.FeedMeet;
import com.wss.webservicestudy.web.feed.service.FeedMeetService;
import com.wss.webservicestudy.web.feed.type.ParticipantStatus;
import com.wss.webservicestudy.web.user.entity.User;
import com.wss.webservicestudy.web.user.service.UserService;

public class CreateChanger extends ParticipationChanger  {
    private final ParticipantStatus status = ParticipantStatus.APPLYING;

    public CreateChanger(FeedMeetService feedMeetService, UserService userService) {
        super(feedMeetService, userService);
    }

    @Override
    protected void checkChangeAvailable(FeedMeet feedMeet, User actor) {
        checkWriterPermission(actor);
    }

    @Override
    protected void changeFeedMeetStatus(FeedMeet feedMeet) {

    }

    @Override
    protected void changeParticipantNumber(Feed feed, User actor) {

    }

}
