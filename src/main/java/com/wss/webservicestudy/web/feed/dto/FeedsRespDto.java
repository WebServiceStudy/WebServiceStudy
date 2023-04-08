package com.wss.webservicestudy.web.feed.dto;

import com.wss.webservicestudy.web.category.entity.Category;
import com.wss.webservicestudy.web.feed.entity.Feed;
import com.wss.webservicestudy.web.feed.type.FeedStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class FeedsRespDto {

    private Long id;

    private String writerName;

    private Category category;

    private String title;

    private String content;

    private FeedStatus status;

    private LocalDateTime date;

    private String addr;

    private int maxUser;

    private int minAge;

    private int maxAge;

    private int curMale;

    private int curFemale;

    private int views;

    public FeedsRespDto(Feed feed) {
        this.id = feed.getId();
        this.writerName = feed.getWriterName();
        this.category = feed.getCategory();
        this.title = feed.getTitle();
        this.content = feed.getContent();
        this.status = feed.getStatus();
        this.date = feed.getDate();
        this.addr = feed.getAddr();
        this.maxUser = feed.getMaxUser();
        this.minAge = feed.getMinAge();
        this.maxAge = feed.getMaxAge();
        this.views = feed.getViews();
    }
}
