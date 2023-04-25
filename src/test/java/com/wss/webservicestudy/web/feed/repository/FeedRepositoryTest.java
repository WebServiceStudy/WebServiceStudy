package com.wss.webservicestudy.web.feed.repository;

import com.wss.webservicestudy.web.feed.dto.CreateFeedDto;
import com.wss.webservicestudy.web.feed.entity.Feed;
import com.wss.webservicestudy.web.feed.mapper.FeedMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FeedRepositoryTest {
    @Autowired
    private FeedRepository feedRepository;

    @Test
    public void save() {
        // given
        String title = "WebServiceStudy1";
        String content = "미팅 웹 페이지 만들기 스터디";
                LocalDateTime date = LocalDateTime.of(2023,5,8,10,0,0);
//        String  date = "2023-05-08 10:00";
        String addr = "강남";
        String latitude = "latitude";
        String longitude = "longitude";
        Integer minAge = 20;
        Integer maxAge = 31;
        boolean genderDivisionYn = false;
        Integer maxUser = 1;
        Integer maxMale = null;
        Integer maxFemale = null;

        CreateFeedDto dto = CreateFeedDto.builder()
                .title(title)
                .content(content)
                .date(date)
                .addr(addr)
                .latitude(latitude)
                .longitude(longitude)
                .maxUser(maxUser)
                .minAge(minAge)
                .maxAge(maxAge)
                .genderDivisionYn(genderDivisionYn)
                .maxFemale(maxMale)
                .maxMale(maxFemale)
                .build();

        // when
        Feed feed = feedRepository.save(FeedMapper.INSTANCE.toFeed(dto));

        // then
        assertThat(feed.getTitle()).isEqualTo(title);
//        assertThat(userList).extracting("name").containsOnly("John", "Jane");
    }

    @Test
    public void reade() {
        Feed feed = feedRepository.findByIdWithFeedMeets((long) 2);

        // then
        assertThat(feed.getTitle()).isEqualTo("WebServiceStudy1");
    }
}
