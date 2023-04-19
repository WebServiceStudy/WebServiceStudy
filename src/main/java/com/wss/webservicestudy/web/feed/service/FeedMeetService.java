package com.wss.webservicestudy.web.feed.service;

import com.wss.webservicestudy.web.feed.entity.Feed;
import com.wss.webservicestudy.web.feed.entity.FeedMeet;
import com.wss.webservicestudy.web.feed.repository.FeedMeetRepository;
import com.wss.webservicestudy.web.feed.repository.FeedRepository;
import com.wss.webservicestudy.web.feed.status.ApproveChanger;
import com.wss.webservicestudy.web.feed.status.CancelChanger;
import com.wss.webservicestudy.web.feed.status.ApplyChanger;
import com.wss.webservicestudy.web.feed.status.RefusalChanger;
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
    public FeedMeet apply(final Long feedId) {
        Feed feed = readFeed(feedId);
        User user = userService.findCurrentUser();

        // FeedMeet 존재 체크
        FeedMeet feedMeet = feedMeetRepository.findByFeedIdAndUserId(feedId,user.getId()).orElseGet(()->create(feed, user));
        ApplyChanger participation = new ApplyChanger(read(feedMeet.getId()), userService);

        return participation.changeStatus();
    }
    private Feed readFeed(final Long feedId){
        return feedRepository.findById(feedId).orElseThrow(()-> new IllegalArgumentException("feed 없음. id="+feedId));
    }

    private FeedMeet create(Feed feed, User user) {
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
        ApproveChanger participation = new ApproveChanger(read(feedMeetId), userService);
        return participation.changeStatus();
    }

    @Transactional
    public FeedMeet cancel(final Long feedMeetId) {
        CancelChanger participation = new CancelChanger(read(feedMeetId), userService);
        return participation.changeStatus();
    }

    @Transactional
    public FeedMeet refusal(final Long feedMeetId) {
        RefusalChanger participation = new RefusalChanger(read(feedMeetId), userService);
        return participation.changeStatus();
    }

    @Transactional
    public void delete(final Long feedMeetId) {
        FeedMeet feedMeet = read(feedMeetId);
//        feedMeet.getFeed().checkWriter(userService.findCurrentUser());
        feedMeetRepository.delete(read(feedMeetId));
    }
}
