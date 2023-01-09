package com.wss.webservicestudy.web.feed.mapper;

import com.wss.webservicestudy.web.feed.dto.CreateFeedDto;
import com.wss.webservicestudy.web.feed.entity.Feed;
import org.junit.Test;

import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;
public class FeedMapperTest {
    @Test
    public void shouldMapDtoToFeed(){
        // given
        String title = "WebServiceStudy";
        String content = "미팅 웹 페이지 만들기 스터디";
        LocalDateTime date = LocalDateTime.of(2023,1,8,10,0,0);
        String addr = "강남";
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

        Feed feed = FeedMapper.INSTANCE.toFeed(requestDto);

        assertThat(feed).isNotNull();
        assertThat(feed.getTitle()).isEqualTo(title);
        assertThat(feed.getContent()).isEqualTo(content);
        assertThat(feed.getDate()).isEqualTo(date);
    }
}
