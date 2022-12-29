package com.wss.webservicestudy.web.feed.service;

import com.wss.webservicestudy.web.feed.entity.Feed;
import com.wss.webservicestudy.web.feed.entity.FeedMeet;
import com.wss.webservicestudy.web.feed.repository.FeedRepository;
import lombok.extern.slf4j.Slf4j;

import com.wss.webservicestudy.web.feed.dto.CreateFeedDto;
import com.wss.webservicestudy.web.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class FeedService {

    private final FeedRepository feedRepository;
    private final UserRepository userRepository;

    public FeedService(FeedRepository feedRepository, UserRepository userRepository) {
        this.feedRepository = feedRepository;
        this.userRepository = userRepository;
    }

    public Feed findOne(Long feedId) {
        return feedRepository.findById(feedId).get();
    }

    @Transactional
    public Long create(CreateFeedDto feedDto) {
        feedDto.setWriter(userRepository.findByEmail("jieun0502@gmail.com")); // 로그인 한사람으로 변경 해야함.
        return feedRepository.save(feedDto.toEntity()).getId();
    }
}
