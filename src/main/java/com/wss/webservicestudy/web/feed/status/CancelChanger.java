package com.wss.webservicestudy.web.feed.status;

import com.wss.webservicestudy.web.feed.entity.Feed;
import com.wss.webservicestudy.web.feed.entity.FeedMeet;
import com.wss.webservicestudy.web.feed.service.FeedMeetService;
import com.wss.webservicestudy.web.feed.type.ParticipantStatus;
import com.wss.webservicestudy.web.user.entity.User;
import com.wss.webservicestudy.web.user.service.UserService;

import java.util.Arrays;
import java.util.List;

public class CancelChanger extends ParticipationChanger {

    private final ParticipantStatus status = ParticipantStatus.CANCEL;
    private final List<ParticipantStatus> preStatus = Arrays.asList(ParticipantStatus.APPLYING, ParticipantStatus.PARTICIPATING);

    public CancelChanger(FeedMeetService feedMeetService, UserService userService) {
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
        checkParticipant(feedMeet, currentUser);
        checkIsWriterSelf(feedMeet);
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
}
