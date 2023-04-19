package com.wss.webservicestudy.web.feed.repository;

import com.wss.webservicestudy.web.feed.entity.FeedMeet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FeedMeetRepository extends JpaRepository<FeedMeet, Long> {

    Long countByFeedId(Long feedId);

    Optional<FeedMeet> findByFeedIdAndUserId(Long feedId, Long userId);
}
