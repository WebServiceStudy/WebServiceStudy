package com.wss.webservicestudy.web.feed.service;

import com.wss.webservicestudy.web.feed.entity.FeedMeet;
import com.wss.webservicestudy.web.feed.repository.FeedMeetRepository;
import com.wss.webservicestudy.web.feed.repository.FeedRepository;
import com.wss.webservicestudy.web.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeedMeetService {
    private final FeedMeetRepository feedMeetRepository;
    private final FeedService feedService;
    private final UserRepository userRepository;

    @Transactional
    public FeedMeet create(final Long feedId) {
        return feedMeetRepository.save(FeedMeet.builder()
                .feed(feedService.findOne(feedId))
                .user(userRepository.findByEmail("jieun0502@gmail.com"))//?:: 로그인user
                .build());
    }

    @Transactional
    public FeedMeet read(final Long feedMeetId) {
        Optional<FeedMeet> optionalFeedMeet = feedMeetRepository.findById(feedMeetId);
        return optionalFeedMeet.orElseThrow(()->
                new IllegalArgumentException("feedMeet 없음. id="+feedMeetId));
    }

    @Transactional
    public FeedMeet update(final Long feedMeetId) {
//        if(feedMeet.getFeed().getWriter().getId() != 로그인유저id) // ?:: 작성자만 승인 가능
        return read(feedMeetId).approve();
    }

    @Transactional
    public void delete(final Long feedMeetId) {
//        if(feedMeet.getFeed().getWriter().getId() != 로그인유저id) // ?:: 작성자만 거절 가능
        feedMeetRepository.delete(read(feedMeetId));
    }
}
