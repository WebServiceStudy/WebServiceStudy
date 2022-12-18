package com.wss.webservicestudy.web.feed.service;

import com.wss.webservicestudy.web.feed.entity.Feed;
import com.wss.webservicestudy.web.feed.entity.FeedMeet;
import com.wss.webservicestudy.web.feed.repository.FeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedService {

    @Autowired
    FeedRepository feedRepository;

    public Feed findOne(Long feedId) {
        return feedRepository.findOne(feedId);
    }

    public long saveFeed(Feed feed) {
        FeedMeet.createFeedMeet(feed);
        feedRepository.save(feed);
        return feed.getId();
    }
}
