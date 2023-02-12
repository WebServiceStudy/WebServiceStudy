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

    private final FeedService feedService;

    private final UserService userService;

    @Transactional
    public FeedMeet create(final Long feedId) {
        Feed feed = feedService.findOne(feedId);
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
        User currentUser = userService.findCurrentUser();
        currentUser.checkIsWritable();
        return feedMeet.approveByWriter(currentUser);
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
        feedMeet.getFeed().checkWriter(userService.findCurrentUser());
        feedMeetRepository.delete(read(feedMeetId));
    }
}
