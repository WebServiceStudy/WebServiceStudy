package com.wss.webservicestudy.web.feed.mapper;

import com.wss.webservicestudy.web.feed.dto.CreateFeedDto;
import com.wss.webservicestudy.web.feed.dto.FeedRespDto;
import com.wss.webservicestudy.web.feed.entity.Feed;
import com.wss.webservicestudy.web.feed.repository.FeedRepository;
import com.wss.webservicestudy.web.user.entity.User;
import com.wss.webservicestudy.web.user.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;
@RunWith(SpringRunner.class)
@SpringBootTest
public class FeedMapperTest {
    @Autowired
    FeedRepository feedRepository;
    @Autowired
    private UserRepository userRepository;
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
    @Test
    @Transactional
    public void shouldMapFeedToFeedRespDto(){
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
        User user = userRepository.findByEmail("jieun0502@gmail.com");
        feed.setWriter(user);

        FeedRespDto feedRespDto = FeedMapper.INSTANCE.toFeedRespDto(feed);
        assertThat(feedRespDto).isNotNull();
        assertThat(feedRespDto.getWriterId()).isEqualTo(user.getId());
        assertThat(feedRespDto.getTitle()).isEqualTo(title);
        assertThat(feedRespDto.getContent()).isEqualTo(content);
        assertThat(feedRespDto.getDate()).isEqualTo(date);
    }
}
