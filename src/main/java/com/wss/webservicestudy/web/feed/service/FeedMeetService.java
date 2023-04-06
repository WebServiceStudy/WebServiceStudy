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
        Feed feed = feedRepository.findById(feedId).get(); // 존재 체크 필요
        User user = userService.findCurrentUser();

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

    @Transactional
    public FeedMeet approve(final Long feedMeetId) {
        FeedMeet feedMeet = read(feedMeetId);
        User currentUser = userService.findCurrentUser();

        checkAvailableToApproveByFeedMeetAndActor(feedMeet, currentUser);

        return feedMeet.approve();
    }

    @Transactional
    public FeedMeet cancel(final Long feedMeetId) {
        FeedMeet feedMeet = read(feedMeetId);
        User currentUser = userService.findCurrentUser();
        checkAvailableToCancelByFeedMeetAndActor(feedMeet, currentUser);

        return feedMeet.cancel();
    }
    @Transactional
    public FeedMeet refusal(final Long feedMeetId) {
        FeedMeet feedMeet = read(feedMeetId);
        User currentUser = userService.findCurrentUser();
        checkAvailableToRefusalByFeedMeetAndActor(feedMeet,currentUser);

        return feedMeet.refusal();
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

    private void checkAvailableToApproveByFeedMeetAndActor(FeedMeet feedMeet, User actor){
        if(!actor.isWritable()){
            throw new IllegalArgumentException("쓰기 권한 없음");
        }
        if (!feedMeet.isFeedWriter(actor)) {
            throw new IllegalArgumentException("해당 게시글의 작성자가 아닙니다.");
        }
        if (!feedMeet.isAvailableToApproveStatus()) {
            throw new IllegalArgumentException("승인할 수 없는 상태입니다. 상태 = " + feedMeet.getStatus().getName());
        }
    }
    private void checkAvailableToCancelByFeedMeetAndActor(FeedMeet feedMeet, User actor){
        if(!feedMeet.equalsParticipant(actor)) {
            throw new IllegalArgumentException("본인의 신청내역만 취소가 가능합니다.");
        }
        if (!feedMeet.isAvailableToCancelStatus()) {
            throw new IllegalArgumentException("승인할 수 없는 상태입니다. 상태 = " + feedMeet.getStatus().getName());
        }
        if(feedMeet.isWriterSelf()) {
            throw new IllegalArgumentException("게시글의 작성자는 항상 참여상태여야 합니다.");
        }
    }
    private void checkAvailableToRefusalByFeedMeetAndActor(FeedMeet feedMeet, User actor){
        if(!actor.isWritable()){
            throw new IllegalArgumentException("쓰기 권한 없음");
        }
        if (!feedMeet.isFeedWriter(actor)) {
            throw new IllegalArgumentException("해당 게시글의 작성자가 아닙니다.");
        }
        if (!feedMeet.isAvailableToRefusalStatus()) {
            throw new IllegalArgumentException("승인할 수 없는 상태입니다. 상태 = " + feedMeet.getStatus().getName());
        }
        if(feedMeet.isWriterSelf()) {
            throw new IllegalArgumentException("게시글의 작성자는 항상 참여상태여야 합니다.");
        }
    }
}
