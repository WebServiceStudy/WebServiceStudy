package com.wss.webservicestudy.web.feed.dto;

import com.wss.webservicestudy.web.category.entity.Category;
import com.wss.webservicestudy.web.feed.entity.Feed;
import com.wss.webservicestudy.web.feed.entity.FeedMeet;
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

    private Category category;

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

    private int views;

    @Builder
    public FeedRespDto(Long id, Long writerId, String writerName, List<FeedMeet> feedMeets, String title, String content, FeedStatus status, LocalDateTime date, String addr, String latitude, String longitude, int maxUser, int minAge, int maxAge, int curMale, int curFemale) {
        this.id = id;
        this.writerId = writerId;
        this.writerName = writerName;
        this.feedMeets = feedMeets
                .stream()
                .map(FeedMeetRespDto::new)
                .collect(Collectors.toList());
        this.title = title;
        this.content = content;
        this.status = status;
        this.date = date;
        this.addr = addr;
        this.latitude = latitude;
        this.longitude = longitude;
        this.maxUser = maxUser;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.curMale = curMale;
        this.curFemale = curFemale;
    }

    public FeedRespDto(Feed feed) {
        this.id = feed.getId();
        this.writerId = feed.getWriterId();
        this.writerName = feed.getWriterName();
        this.feedMeets = feed.getFeedMeets()
                .stream()
                .map(FeedMeetRespDto::new)
                .collect(Collectors.toList());
        this.category = feed.getCategory();
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
        this.views = feed.getViews();
    }
}
