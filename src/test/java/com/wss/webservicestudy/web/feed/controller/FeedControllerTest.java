package com.wss.webservicestudy.web.feed.controller;

import com.wss.webservicestudy.WebservicestudyApplication;
import com.wss.webservicestudy.web.feed.dto.CreateFeedDto;
import com.wss.webservicestudy.web.feed.dto.UpdateFeedDto;
import com.wss.webservicestudy.web.feed.entity.Feed;
import com.wss.webservicestudy.web.feed.repository.FeedRepository;
import com.wss.webservicestudy.web.user.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = WebservicestudyApplication.class)
public class FeedControllerTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private FeedRepository feedRepository;

    @Autowired
    private UserRepository userRepository;

//    @After
//    public void tearDown() throws Exception{
//        feedRepository.deleteAll();
//    }

    @Test
    public void createFeed() throws Exception{
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

        String url = "http://localhost:" + port + "/feed";

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

        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);
        List<Feed> all = feedRepository.findAll();
        assertThat(all.get(all.size()-1).getTitle()).isEqualTo(title);
    }

    @Test
    public void updateFeed() throws Exception{
        // given
        String title = "WebServiceStudy2";
        String content = "미팅 웹 페이지 만들기 스터디2";
        LocalDateTime date = LocalDateTime.of(2023,1,8,12,22,0);
        String addr = "강남 스터디룸";
        String latitude = "latitude2";
        String longitude = "longitude2";
        int maxUser = 6;
        int minAge = 22;
        int maxAge = 32;

        Long updatedFeedId = (long)1;
        String url = "http://localhost:" + port + "/feed/" + updatedFeedId;

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
        HttpEntity<UpdateFeedDto> requestEntity = new HttpEntity<>(requestDto);

        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        assertThat(feedRepository.findById(updatedFeedId).get().getTitle()).isEqualTo(title);
    }
}
