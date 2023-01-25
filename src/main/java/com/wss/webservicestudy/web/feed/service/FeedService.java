package com.wss.webservicestudy.web.feed.service;

import com.wss.webservicestudy.web.feed.dto.CreateFeedDto;
import com.wss.webservicestudy.web.feed.dto.FeedRespDto;
import com.wss.webservicestudy.web.feed.dto.UpdateFeedDto;
import com.wss.webservicestudy.web.feed.entity.Feed;
import com.wss.webservicestudy.web.feed.entity.FeedMeet;
import com.wss.webservicestudy.web.feed.mapper.FeedMapper;
import com.wss.webservicestudy.web.feed.repository.FeedRepository;
import com.wss.webservicestudy.web.user.entity.User;
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

    private final FeedMeetService feedMeetService;

    @Transactional(readOnly = true)
    public List<FeedRespDto> findAllDesc() {
        return feedRepository.findAllByOrderByIdDesc()
                .stream()
                .map(FeedRespDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public FeedRespDto findRespById(Long feedId) {
        return new FeedRespDto(findOne(feedId));
    }

    @Transactional(readOnly = true)
    public Feed findOne(Long feedId) {
        return feedRepository.findById(feedId)
                .orElseThrow(() -> new IllegalArgumentException("feed 없음. id = " + feedId));
    }

    @Transactional
    public Feed create(CreateFeedDto feedDto, String userEmail) {
        User user = userRepository.findByEmail(userEmail);
        feedDto.setWriter(user);
        Feed feed = FeedMapper.INSTANCE.toFeed(feedDto);
        feedRepository.save(feed);

        FeedMeet feedMeet = feedMeetService.create(feed, user);
        feedMeetService.update(feedMeet.getId());
        return feed;
    }

    @Transactional
    public Feed update(final Long feedId, UpdateFeedDto feedDto, Long userId) {
        Feed feed = findOne(feedId);
        feed.checkWriter(userId);
        return feed.update(feedDto);
    }

    @Transactional
    public Long delete(Long feedId, Long userId) {
        findOne(feedId).checkWriter(userId);
        feedRepository.deleteById(feedId);
        return feedId;
    }
}
