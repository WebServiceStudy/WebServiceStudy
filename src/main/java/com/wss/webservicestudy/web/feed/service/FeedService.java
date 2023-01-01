package com.wss.webservicestudy.web.feed.service;

import com.wss.webservicestudy.web.feed.dto.CreateFeedDto;
import com.wss.webservicestudy.web.feed.dto.UpdateFeedDto;
import com.wss.webservicestudy.web.feed.entity.Feed;
import com.wss.webservicestudy.web.feed.entity.FeedMeet;
import com.wss.webservicestudy.web.feed.repository.FeedRepository;
import lombok.extern.slf4j.Slf4j;

import com.wss.webservicestudy.web.feed.dto.CreateFeedDto;
import com.wss.webservicestudy.web.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

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
        Optional<Feed> optionalFeed = feedRepository.findById(feedId);
        return optionalFeed.orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id=" + feedId));
    }

    @Transactional
    public Long create(CreateFeedDto feedDto) {
        feedDto.setWriter(userRepository.findByEmail("jieun0502@gmail.com")); //?_? 로그인 한사람으로 변경 해야함.
        return feedRepository.save(feedDto.toEntity()).getId();
    }

    @Transactional
    public Long update(final Long feedId, UpdateFeedDto feedDto) {
        Feed feed = findOne(feedId);
        feed.update(feedDto);
        return feedId;
    }
}
