package com.wss.webservicestudy.web.feed.service;

import com.wss.webservicestudy.web.feed.entity.Feed;
import com.wss.webservicestudy.web.feed.type.FeedStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class FeedServiceTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    FeedService feedService;

    @Test
    public void 저장() {
        Feed feed = createFeed();
        feedService.saveFeed(feed);
        Feed result = feedService.findOne(feed.getId());
        assertThat(feed).isEqualTo(result);
    }

    private Feed createFeed() {
        Feed feed = new Feed();
        feed.setTitle("피드1");
        feed.setContent("피드 내용1");
        feed.setStatus(FeedStatus.RECRUITING);
        feed.setAddr("강남역");
        feed.setDate(new Date());
        em.persist(feed);
        return feed;
    }
}