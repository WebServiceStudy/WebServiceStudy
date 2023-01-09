package com.wss.webservicestudy.web.feed.service;

import com.wss.webservicestudy.web.feed.dto.CreateFeedDto;
import com.wss.webservicestudy.web.feed.dto.FeedRespDto;
import com.wss.webservicestudy.web.feed.dto.UpdateFeedDto;
import com.wss.webservicestudy.web.feed.entity.Feed;
import com.wss.webservicestudy.web.feed.mapper.FeedMapper;
import com.wss.webservicestudy.web.feed.repository.FeedRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.wss.webservicestudy.web.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeedService {

    private final FeedRepository feedRepository;
    private final UserRepository userRepository;

    @Transactional
    public List<FeedRespDto> findAllDesc() {
        return feedRepository.findAllByOrderByIdDesc()
                .stream()
                .map(FeedRespDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public FeedRespDto findRespById(Long feedId) {
        return new FeedRespDto(findOne(feedId));
    }

    @Transactional
    public Feed findOne(Long feedId) {
        return feedRepository.findById(feedId)
                .orElseThrow(() -> new IllegalArgumentException("feed 없음. id = " + feedId));
    }

    @Transactional
    public Feed create(CreateFeedDto feedDto) {
        feedDto.setWriter(userRepository.findByEmail("jieun0502@gmail.com")); //?::로그인 user
        return feedRepository.save(FeedMapper.INSTANCE.toFeed(feedDto));
    }

    @Transactional
    public Feed update(final Long feedId, UpdateFeedDto feedDto) {
        Feed feed = findOne(feedId);
//        if(feed.getWriter().getId() != 로그인유저Id) //?::작성자만 수정이 가능합니다.
        return feed.update(feedDto);
    }
}
