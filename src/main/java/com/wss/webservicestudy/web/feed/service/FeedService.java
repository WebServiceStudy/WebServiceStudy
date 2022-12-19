package com.wss.webservicestudy.web.feed.service;

import com.wss.webservicestudy.web.feed.dto.CreateFeedDto;
import com.wss.webservicestudy.web.feed.repository.FeedRepository;
import com.wss.webservicestudy.web.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FeedService {
    private final FeedRepository feedRepository;
    private final UserRepository userRepository;

    public FeedService(FeedRepository feedRepository, UserRepository userRepository) {
        this.feedRepository = feedRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Long create(CreateFeedDto feedDto) {
        feedDto.setWriter(userRepository.findByEmail("jieun0502@gmail.com")); // 로그인 한사람으로 변경 해야함.
        return feedRepository.save(feedDto.toEntity()).getId();
    }
}
