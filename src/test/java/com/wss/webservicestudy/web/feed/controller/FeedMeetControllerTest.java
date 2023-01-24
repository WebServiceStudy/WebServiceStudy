package com.wss.webservicestudy.web.feed.controller;

import com.wss.webservicestudy.WebservicestudyApplication;
import com.wss.webservicestudy.web.feed.entity.Feed;
import com.wss.webservicestudy.web.feed.entity.FeedMeet;
import com.wss.webservicestudy.web.feed.repository.FeedMeetRepository;
import com.wss.webservicestudy.web.feed.repository.FeedRepository;
import com.wss.webservicestudy.web.feed.type.ParticipantStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = WebservicestudyApplication.class)
public class FeedMeetControllerTest {
    @LocalServerPort private int port;
    @Autowired private TestRestTemplate testRestTemplate;
    @Autowired private FeedRepository feedRepository;
    @Autowired private FeedMeetRepository feedMeetRepository;

    String getUrl(){
        return "http://localhost:" + port + "/api/feed/feedmeet";
    }

    @Test
    public void create(){
        List<Feed> feeds = feedRepository.findAll();
        Long feedId = feeds.get(feeds.size()-1).getId();
        String url = getUrl() + "?feed=" + feedId;
        int sizeBeforeCreate = feedMeetRepository.findAll().size();

        ResponseEntity<Long> responseEntity = testRestTemplate.postForEntity(url,null, Long.class);
        assertThat(feedMeetRepository.findAll().size()).isEqualTo(sizeBeforeCreate+1);
    }

    @Test
    public void read(){
        List<FeedMeet> feedMeets = feedMeetRepository.findAll();
        Long feedMeetId = feedMeets.get(feedMeets.size()-1).getId();
        String url = getUrl() + "/" + feedMeetId;

        ResponseEntity<Long> responseEntity = testRestTemplate.getForEntity(url, Long.class);
        assertThat(responseEntity.getBody()).isEqualTo(feedMeetId);
    }

    @Test
    public void update(){
        List<FeedMeet> feedMeets = feedMeetRepository.findAll();
        Long feedMeetId = feedMeets.get(feedMeets.size()-1).getId();
        String url = getUrl() + "/" + feedMeetId;

        assertThat(feedMeetRepository.findById(feedMeetId).get().getStatus()).isEqualTo(ParticipantStatus.APPLYING);
        ResponseEntity<Long> responseEntity = testRestTemplate.exchange(url, HttpMethod.PUT, null, Long.class);
        assertThat(feedMeetRepository.findById(feedMeetId).get().getStatus()).isEqualTo(ParticipantStatus.PARTICIPATING);
    }

    @Test
    public void delete(){
        List<FeedMeet> feedMeets = feedMeetRepository.findAll();
        Long feedMeetId = feedMeets.get(feedMeets.size()-1).getId();
        String url = getUrl() + "/" + feedMeetId;
        int sizeBeforeCreate = feedMeetRepository.findAll().size();

        ResponseEntity<Long> responseEntity = testRestTemplate.exchange(url, HttpMethod.DELETE, null, Long.class);
        assertThat(feedMeetRepository.findAll().size()).isEqualTo(sizeBeforeCreate-1);
    }
}
