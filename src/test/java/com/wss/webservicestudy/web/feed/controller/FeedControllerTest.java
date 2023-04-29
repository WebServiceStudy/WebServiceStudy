package com.wss.webservicestudy.web.feed.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.wss.webservicestudy.WebservicestudyApplication;
import com.wss.webservicestudy.web.feed.dto.CreateFeedDto;
import com.wss.webservicestudy.web.feed.dto.UpdateFeedDto;
import com.wss.webservicestudy.web.feed.entity.Feed;
import com.wss.webservicestudy.web.feed.repository.FeedMeetRepository;
import com.wss.webservicestudy.web.feed.repository.FeedRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = WebservicestudyApplication.class)
@AutoConfigureMockMvc
public class FeedControllerTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private FeedRepository feedRepository;
    @Autowired
    private FeedMeetRepository feedMeetRepository;
    @Autowired
    private MockMvc mockMvc;

    //    @After
//    public void tearDown() throws Exception{
//        feedRepository.deleteAll();
//    }

    String getUrl(){
        return "http://localhost:" + port + "/api/feed";
    }
    String getUrl(String addUrl){ return "/api/feed"+addUrl; }
    String getJsonData(Object value) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return objectMapper.writeValueAsString(value);
    }

    CreateFeedDto getCreateFeedDto(){
        // Given
        String title = "시큐리티 유틸3";
        String content = "이용 test";
        LocalDateTime date = LocalDateTime.of(2024,5,8,10,0,0);
//        String  date = "2024-04-27T13:30:00";
        String addr = "나이시";
        String latitude = "latitude";
        String longitude = "longitude";
        Integer minAge = 25;
        Integer maxAge = 30;
        boolean genderDivisionYn = true;
        Integer maxUser = null;
        Integer maxMale = 2;
        Integer maxFemale = 2;

        return CreateFeedDto.builder()
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
    }
    @Test
    @WithUserDetails(value = "jieun0502@gmail.com")
    public void create() throws Exception {
        String param = getJsonData(getCreateFeedDto());

        mockMvc.perform(post(getUrl(""))
                        .content(param)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Transactional
    @Test
    public void readFeed() throws Exception {
        mockMvc.perform(get(getUrl("/2")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("SUCCESS"))
                .andExpect(jsonPath("$.result.title").value("WebServiceStudy1"));
    }
    @Transactional
    @Test
    public void readFeedStatus() throws Exception {
        mockMvc.perform(get(getUrl("/18/status")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("SUCCESS"))
                .andExpect(jsonPath("$.result").value("RECRUITING"));
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
