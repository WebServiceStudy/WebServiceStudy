package com.wss.webservicestudy.web.feed.service;

import com.wss.webservicestudy.web.common.util.SecurityUtil;
import com.wss.webservicestudy.web.feed.dto.CreateFeedDto;
import com.wss.webservicestudy.web.feed.dto.FeedRespDto;
import com.wss.webservicestudy.web.feed.dto.FeedsRespDto;
import com.wss.webservicestudy.web.feed.dto.UpdateFeedDto;
import com.wss.webservicestudy.web.feed.entity.Feed;
import com.wss.webservicestudy.web.feed.entity.FeedMeet;
import com.wss.webservicestudy.web.feed.mapper.FeedMapper;
import com.wss.webservicestudy.web.feed.repository.FeedRepository;
import com.wss.webservicestudy.web.feed.type.FeedDeleteYn;
import com.wss.webservicestudy.web.feed.type.FeedStatus;
import com.wss.webservicestudy.web.user.entity.User;
import com.wss.webservicestudy.web.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeedService {

    private final FeedRepository feedRepository;
    private final FeedMeetService feedMeetService;
    private final UserService userService;

    @Transactional(readOnly = true)
    public List<FeedsRespDto> findAllDesc(Pageable pageable) {
        return feedRepository.findAllByOrderByIdDesc(pageable)
                .stream()
                .map(FeedsRespDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<FeedsRespDto> findUserFeeds(Pageable pageable) {
        return feedRepository.findAllByWriter(pageable, userService.findCurrentUser())
                .stream()
                .map(FeedsRespDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<FeedsRespDto> findUserAppliedFeeds(Pageable pageable) {
        return feedRepository.findAllByFeedMeets_User(pageable, userService.findCurrentUser())
                .stream()
                .map(FeedsRespDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public FeedRespDto findRespById(Long feedId) {
        Feed feed = feedRepository.findByIdWithFeedMeets(feedId);
        // TODO : feed 상세 페이지 이동 시에만 증가
        //feed.addViews();
        return FeedMapper.INSTANCE.toFeedRespDto(feed);
    }

    public Feed findOne(Long feedId) {
        return feedRepository.findById(feedId)
                .orElseThrow(() -> new IllegalArgumentException("feed 없음. id = " + feedId));
    }

    @Transactional(readOnly = true)
    public FeedStatus findFeedStatus(Long feedId) {
        return findOne(feedId).getStatus();
    }

    @Transactional
    public Feed create(CreateFeedDto feedDto) {
        Feed feed = FeedMapper.INSTANCE.toFeed(feedDto);
        feedRepository.save(feed);

        FeedMeet feedMeet = feedMeetService.create(feed, feed.getWriter());
        feedMeetService.approve(feedMeet.getId());
        return feed;
    }

    //TODO: isFeedWriter 값만 들고오고 하는거 없음.
    @Transactional
    public Feed update(final Long feedId, UpdateFeedDto feedDto) {
        Feed feed = findOne(feedId);
        feed.isFeedWriter(SecurityUtil.getLoginUserWithWritable());
        return feed.update(feedDto);
    }
    @Transactional
    public FeedRespDto updateStatus(Long feedId, FeedStatus feedStatus) {
        Feed feed = findOne(feedId);
        feed.isFeedWriter(SecurityUtil.getLoginUserWithWritable());

        if(!feed.existsParticipant()){
            throw new IllegalArgumentException("참가자가 2명이상은 되어야합니다.");
        }
        feed.setStatus(feedStatus);
        return FeedMapper.INSTANCE.toFeedRespDto(feed);
    }

    @Transactional
    public Long delete(Long feedId) {
        Feed feed = findOne(feedId);
        feed.isFeedWriter(SecurityUtil.getLoginUserWithWritable());

        if(feed.existsParticipant()){
            feed.setDeleteYn(FeedDeleteYn.DELETED);
            return feedId;
        }

        feedRepository.deleteById(feedId);
        return feedId;
    }
}
