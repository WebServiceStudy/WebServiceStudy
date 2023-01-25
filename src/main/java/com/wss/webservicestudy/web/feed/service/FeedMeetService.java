package com.wss.webservicestudy.web.feed.service;

import com.wss.webservicestudy.web.feed.entity.Feed;
import com.wss.webservicestudy.web.feed.entity.FeedMeet;
import com.wss.webservicestudy.web.feed.repository.FeedMeetRepository;
import com.wss.webservicestudy.web.feed.repository.FeedRepository;
import com.wss.webservicestudy.web.user.entity.User;
import com.wss.webservicestudy.web.user.repository.UserRepository;
import com.wss.webservicestudy.web.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeedMeetService {
    private final FeedMeetRepository feedMeetRepository;
    private final UserRepository userRepository;

    private final FeedRepository feedRepository;

    private final UserService userService;

    @Transactional
    public FeedMeet create(final Long feedId) {
        return feedMeetRepository.save(FeedMeet.builder()
                .feed(feedRepository.findById(feedId).get())
                .user(userRepository.findByEmail("jieun0502@gmail.com"))//?:: 로그인user
                .build());
    }

    @Transactional
    public FeedMeet create(final Long feedId, String userEmail) {
        FeedMeet feedMeet = feedMeetRepository.save(FeedMeet.builder()
                .feed(feedRepository.findById(feedId).get())
                .user(userRepository.findByEmail(userEmail))
                .build());
        return feedMeet;
    }

    @Transactional
    public FeedMeet create(Feed feed, User user) {
        return feedMeetRepository.save(FeedMeet.builder()
                .feed(feed)
                .user(user)
                .build());
    }

    @Transactional
    public FeedMeet createWriter(Feed feed, User user) {
        FeedMeet feedMeet = FeedMeet.builder()
                .feed(feed)
                .user(user)
                .build();
        return feedMeetRepository.save(feedMeet);
    }

    @Transactional
    public FeedMeet read(final Long feedMeetId) {
        Optional<FeedMeet> optionalFeedMeet = feedMeetRepository.findById(feedMeetId);
        return optionalFeedMeet.orElseThrow(()->
                new IllegalArgumentException("feedMeet 없음. id="+feedMeetId));
    }

    @Transactional
    public FeedMeet approve(final Long feedMeetId) {
        FeedMeet feedMeet = read(feedMeetId);
        return feedMeet.approveByWriter(userService.findCurrentUser());
    }

    @Transactional
    public FeedMeet cancel(final Long feedMeetId) {
        FeedMeet feedMeet = read(feedMeetId);
        return feedMeet.cancelByParticipant(userService.findCurrentUser());
    }

    @Transactional
    public FeedMeet refusal(final Long feedMeetId) {
        FeedMeet feedMeet = read(feedMeetId);
        return feedMeet.refusalByWriter(userService.findCurrentUser());
    }

    @Transactional
    public void delete(final Long feedMeetId) {
        FeedMeet feedMeet = read(feedMeetId);
        feedMeet.getFeed().checkWriter(userService.findCurrentUser().getId());
        feedMeetRepository.delete(read(feedMeetId));
    }

    @Transactional
    public void delete(final Long feedMeetId, Long userId) {
        FeedMeet feedMeet = read(feedMeetId);
        feedMeet.getFeed().checkWriter(userId);
        feedMeetRepository.delete(read(feedMeetId));
    }
}
