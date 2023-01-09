package com.wss.webservicestudy.web.feed.service;

import com.wss.webservicestudy.WebservicestudyApplication;
import com.wss.webservicestudy.web.feed.dto.CreateFeedDto;
import com.wss.webservicestudy.web.feed.dto.UpdateFeedDto;
import com.wss.webservicestudy.web.feed.entity.Feed;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FeedServiceTest {
    @Autowired
    private FeedService feedService;

    @Test
    public void createFeed(){
        String title = "WebServiceStudy";
        String content = "미팅 웹 페이지 만들기 스터디";
        LocalDateTime date = LocalDateTime.of(2023,1,8,10,0,0);
        String addr = "강남1";
        String latitude = "latitude";
        String longitude = "longitude";
        int maxUser = 8;
        int minAge = 20;
        int maxAge = 31;

        CreateFeedDto requestDto = CreateFeedDto.builder()
                .title(title)
                .content(content)
                .date(date)
                .addr(addr)
                .latitude(latitude)
                .longitude(longitude)
                .maxUser(maxUser)
                .minAge(minAge)
                .maxAge(maxAge)
                .build();
        Feed feed = feedService.create(requestDto);
        assertThat(feed.getAddr()).isEqualTo("강남1");
    }


    @Test
    public void readFeed(){
        Feed feed = feedService.findOne((long)29);
        assertThat(feed.getAddr()).isEqualTo("강남 스터디룸");
    }

    @Test
    public void updateFeed(){
        String title = "WebServiceStudy44";
        String content = "미팅 웹 페이지 만들기 스터디2";
        LocalDateTime date = LocalDateTime.of(2023,1,8,12,22,0);
        String addr = "강남 스터디룸";
        String latitude = "latitude2";
        String longitude = "longitude2";
        int maxUser = 6;
        int minAge = 22;
        int maxAge = 32;
        UpdateFeedDto requestDto = UpdateFeedDto.builder()
                .title(title)
                .content(content)
                .date(date)
                .addr(addr)
                .latitude(latitude)
                .longitude(longitude)
                .maxUser(maxUser)
                .minAge(minAge)
                .maxAge(maxAge)
                .build();
        Feed feed = feedService.update((long)29, requestDto);
        assertThat(feed.getAddr()).isEqualTo("강남 스터디룸");
    }
}
