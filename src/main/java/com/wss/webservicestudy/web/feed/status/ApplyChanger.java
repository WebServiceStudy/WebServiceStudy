package com.wss.webservicestudy.web.feed.status;

import com.wss.webservicestudy.web.feed.entity.Feed;
import com.wss.webservicestudy.web.feed.entity.FeedMeet;
import com.wss.webservicestudy.web.feed.type.ParticipantStatus;
import com.wss.webservicestudy.web.user.entity.User;
import com.wss.webservicestudy.web.user.service.UserService;

import java.util.Arrays;
import java.util.List;

public class ApplyChanger extends ParticipationChanger  {
    private final ParticipantStatus STATUS = ParticipantStatus.APPLYING;
    private final List<ParticipantStatus> PRE_STATUS = Arrays.asList(null, ParticipantStatus.CANCEL);

    public ApplyChanger(FeedMeet feedMeet, UserService userService) {
        super(feedMeet, userService);
    }

    @Override
    protected void checkChangeAvailable(User actor) {
        checkStatus();
        checkWriterPermission(actor);
        checkParticipant(actor);
        checkAge(actor.getAge());
    }

    @Override
    protected void changeParticipantNumber(Feed feed, User user) {
        // 변경 없음.
    }

    @Override
    protected ParticipantStatus getNewStatus() {
        return this.STATUS;
    }

    @Override
    protected List<ParticipantStatus> getPreStatusList() {
        return this.PRE_STATUS;
    }
    private void checkParticipant(User actor) {
        if(!getFeedMeet().equalsParticipant(actor)) {
            throw new IllegalArgumentException("본인의 내역만 신청가능합니다.");
        }
    }
}
