package com.wss.webservicestudy.web.feed.service;

import com.wss.webservicestudy.WebservicestudyApplication;
import com.wss.webservicestudy.web.feed.repository.FeedMeetRepository;
import com.wss.webservicestudy.web.feed.repository.FeedRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FeedMeetServiceTest {
    @Autowired FeedMeetService feedMeetService;
    @Autowired FeedRepository feedRepository;
    @Autowired
    FeedMeetRepository feedMeetRepository;

    @Test
    public void create(){
        int index = feedRepository.findAll().size() - 1;
        feedMeetService.create((long)index);
    }

    @Test
    public void read(){
        int index = feedMeetRepository.findAll().size() - 1;
        Long id = feedMeetRepository.findAll().get(index).getId();
        assertThat(feedMeetService.read(id).getId()).isEqualTo(id);

    }

    @Test
    public void update(){
        int index = feedMeetRepository.findAll().size() - 1;
        feedMeetService.update(feedMeetRepository.findAll().get(index).getId());
    }

    @Test
    public void delete(){
        int index = feedMeetRepository.findAll().size() - 1;
        feedMeetService.delete(feedMeetRepository.findAll().get(index).getId());
    }
}
