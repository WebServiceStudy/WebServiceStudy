package com.wss.webservicestudy.web.feed.service;

import com.wss.webservicestudy.web.feed.entity.Feed;
import com.wss.webservicestudy.web.feed.entity.FeedMeet;
import com.wss.webservicestudy.web.feed.repository.FeedMeetRepository;
import com.wss.webservicestudy.web.feed.repository.FeedRepository;
import com.wss.webservicestudy.web.user.entity.User;
import com.wss.webservicestudy.web.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeedMeetService {
    private final FeedMeetRepository feedMeetRepository;

    private final FeedRepository feedRepository;

    private final UserService userService;

    @Transactional
    public FeedMeet create(final Long feedId) {
        Feed feed = feedRepository.findById(feedId).get();
        User user = userService.findCurrentUser();

        return create(feed, user);
    }

    private FeedMeet create(Feed feed, User user) {
        user.checkIsWritable();
        feed.checkAge(user.getAge());
        return feedMeetRepository.save(FeedMeet.builder()
                .feed(feed)
                .user(user)
                .build());
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
        //현재 유저가
        User currentUser = userService.findCurrentUser();
        // 쓰기 가능한 유저인지
        currentUser.checkIsWritable();
        // 글쓴이인지
        feedMeet.getFeed().checkWriter(currentUser);

        return feedMeet.approve();
    }

    @Transactional
    public FeedMeet cancel(final Long feedMeetId) {
        FeedMeet feedMeet = read(feedMeetId);
        //현재 유저가
        User currentUser = userService.findCurrentUser();
        // 참여신청한 유저가 맞는지
        feedMeet.checkParticipant(currentUser);

        return feedMeet.cancel();
    }

    @Transactional
    public FeedMeet refusal(final Long feedMeetId) {
        FeedMeet feedMeet = read(feedMeetId);
        //현재 유저가
        User currentUser = userService.findCurrentUser();
        // 쓰기 가능한 유저인지
        currentUser.checkIsWritable();
        // 글쓴이인지
        feedMeet.getFeed().checkWriter(currentUser);
        return feedMeet.refusal();
    }

    @Transactional
    public void delete(final Long feedMeetId) {
        FeedMeet feedMeet = read(feedMeetId);
        feedMeet.getFeed().checkWriter(userService.findCurrentUser());
        feedMeetRepository.delete(read(feedMeetId));
    }
}
