package com.wss.webservicestudy.web.feed.controller;

import com.wss.webservicestudy.WebservicestudyApplication;
import com.wss.webservicestudy.web.feed.dto.CreateFeedDto;
import com.wss.webservicestudy.web.feed.dto.FeedRespDto;
import com.wss.webservicestudy.web.feed.dto.UpdateFeedDto;
import com.wss.webservicestudy.web.feed.entity.Feed;
import com.wss.webservicestudy.web.feed.repository.FeedMeetRepository;
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
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private FeedMeetRepository feedMeetRepository;

    //    @After
//    public void tearDown() throws Exception{
//        feedRepository.deleteAll();
//    }

    String getUrl(){
        return "http://localhost:" + port + "/api/feed";
    }

    @Transactional
    @Test
    public void readFeed() {
        // User accessToken 필요
        Feed feed = feedRepository.findTop1ByOrderByIdDesc();

        String url = getUrl() + "/" + feed.getId();
        ResponseEntity<FeedRespDto> respDto = restTemplate.getForEntity(url, FeedRespDto.class);
        //assertThat(new FeedRespDto(feed).getTitle()).isEqualTo(respDto.getBody().getTitle());
    }

    @Test
    public void createFeed() throws Exception{
        // given
        String title = "WebServiceStudy1";
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

        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(getUrl(), requestDto, Long.class);
        List<Feed> all = feedRepository.findAll();

        assertThat(all.get(all.size()-1).getTitle()).isEqualTo(title);
    }

    @Test
    public void updateFeed() throws Exception{
        // given
        String title = "WebServiceStudy3";
        String content = "미팅 웹 페이지 만들기 스터디2";
        LocalDateTime date = LocalDateTime.of(2023,1,8,12,22,0);
        String addr = "강남 스터디룸";
        String latitude = "latitude2";
        String longitude = "longitude2";
        int maxUser = 6;
        int minAge = 22;
        int maxAge = 32;

        List<Feed> all = feedRepository.findAll();
        Long updatedFeedId = all.get(all.size()-1).getId();
        String url = getUrl() + "/" + updatedFeedId;

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

        ResponseEntity<Feed> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Feed.class);

        assertThat(feedRepository.findById(updatedFeedId).get().getTitle()).isEqualTo(title);
    }

    @Test
    public void deleteFeed() {
        Feed lastFeed = feedRepository.findTop1ByOrderByIdDesc();
        Long lastFeedId = lastFeed.getId();

        String url = getUrl() + "/" + lastFeedId;
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, HttpEntity.EMPTY, Long.class);

        assertThat(responseEntity.getBody()).isEqualTo(lastFeedId);
        assertThat(feedRepository.findById(lastFeedId)).isNull();
        assertThat(feedMeetRepository.countByFeedId(lastFeedId)).isEqualTo(0L);
    }
}
