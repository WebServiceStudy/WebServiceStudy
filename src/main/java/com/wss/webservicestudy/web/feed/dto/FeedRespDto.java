package com.wss.webservicestudy.web.feed.dto;

import com.wss.webservicestudy.web.feed.entity.Feed;
import com.wss.webservicestudy.web.feed.type.FeedStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class FeedRespDto {

    private Long id;

    private Long writerId;

    private String writerName;

    private List<FeedMeetRespDto> feedMeets = new ArrayList<>();

    private String title;

    private String content;

    private FeedStatus status;

    private LocalDateTime date;

    private String addr;

    private String latitude;

    private String longitude;

    private int maxUser;

    private int minAge;

    private int maxAge;

    private int curMale;

    private int curFemale;

    @Builder
    public FeedRespDto(Feed feed) {
        this.id = feed.getId();
        this.writerId = feed.getWriter().getId();
        this.writerName = feed.getWriter().getName();
        this.feedMeets = feed.getFeedMeets()
                .stream()
                .map(FeedMeetRespDto::new)
                .collect(Collectors.toList());
        this.title = feed.getTitle();
        this.content = feed.getContent();
        this.status = feed.getStatus();
        this.date = feed.getDate();
        this.addr = feed.getAddr();
        this.latitude = feed.getLatitude();
        this.longitude = feed.getLongitude();
        this.maxUser = feed.getMaxUser();
        this.minAge = feed.getMinAge();
        this.maxAge = feed.getMaxAge();
        this.curMale = feed.getCurMale();
        this.curFemale = feed.getCurFemale();
    }
}
