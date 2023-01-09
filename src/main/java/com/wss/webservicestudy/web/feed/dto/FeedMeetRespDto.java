package com.wss.webservicestudy.web.feed.dto;

import com.wss.webservicestudy.web.feed.entity.FeedMeet;
import com.wss.webservicestudy.web.feed.type.ParticipantStatus;
import com.wss.webservicestudy.web.user.type.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FeedMeetRespDto {
    private Long id;

    private Long feedId;

    private Long userId;

    private String userName;

    private Gender gender;

    private ParticipantStatus status;

    public FeedMeetRespDto(FeedMeet feedMeet) {
        this.id = feedMeet.getId();
        this.feedId = feedMeet.getFeed().getId();
        this.userId = feedMeet.getUser().getId();
        this.userName = feedMeet.getUser().getName();
        this.gender = feedMeet.getUser().getGender();
        this.status = feedMeet.getStatus();
    }
}