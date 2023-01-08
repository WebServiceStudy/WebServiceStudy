package com.wss.webservicestudy.web.feed.repository;

import com.wss.webservicestudy.web.feed.entity.FeedMeet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedMeetRepository extends JpaRepository<FeedMeet, Long> {
}
