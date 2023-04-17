package com.wss.webservicestudy.web.feed.service;

import com.wss.webservicestudy.web.feed.entity.Feed;
import com.wss.webservicestudy.web.feed.entity.FeedMeet;
import com.wss.webservicestudy.web.feed.repository.FeedMeetRepository;
import com.wss.webservicestudy.web.feed.repository.FeedRepository;
import com.wss.webservicestudy.web.feed.status.ApproveChanger;
import com.wss.webservicestudy.web.feed.status.CancelChanger;
import com.wss.webservicestudy.web.feed.status.CreateChanger;
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
    public FeedMeet create(final Long feedId) {
        Feed feed = feedRepository.findById(feedId).orElseThrow(()->
                new IllegalArgumentException("feed 없음. id="+feedId));
        User user = userService.findCurrentUser();
        // TODO : FeedMeet 존재 체크 필요

        CreateChanger createChanger = new CreateChanger(this, userService);
        return create(feed, user);
    }

    private FeedMeet create(Feed feed, User user) {
        checkAvailableToCreateByFeedAndActor(feed, user);
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

    private FeedMeet read(Long feedId, Long userId){
        Optional<FeedMeet> optionalFeedMeet = feedMeetRepository.findBy(feedMeetId);
        return optionalFeedMeet.orElseThrow(()->
                new IllegalArgumentException("feedMeet 없음. id="+feedMeetId));
    }

    @Transactional
    public FeedMeet approve(final Long feedMeetId) {
        ApproveChanger participation = new ApproveChanger(this, userService);
        return participation.changeStatus(feedMeetId);
    }

    @Transactional
    public FeedMeet cancel(final Long feedMeetId) {
        CancelChanger participation = new CancelChanger(this, userService);
        return participation.changeStatus(feedMeetId);
    }

    @Transactional
    public FeedMeet refusal(final Long feedMeetId) {
        RefusalChanger participation = new RefusalChanger(this, userService);
        return participation.changeStatus(feedMeetId);
    }

    @Transactional
    public void delete(final Long feedMeetId) {
        FeedMeet feedMeet = read(feedMeetId);
//        feedMeet.getFeed().checkWriter(userService.findCurrentUser());
        feedMeetRepository.delete(read(feedMeetId));
    }

    private void checkAvailableToCreateByFeedAndActor(Feed feed, User actor){
        if(!actor.isWritable()){
            throw new IllegalArgumentException("쓰기 권한 없음");
        }
        // 나이체크
        if (feed.isAvailableAge(actor.getAge())) {
            throw new IllegalArgumentException("요구하는 나이와 다름");
        }
    }
}
