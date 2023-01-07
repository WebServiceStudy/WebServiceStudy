package com.wss.webservicestudy.web.feed.repository;

import com.wss.webservicestudy.web.feed.entity.Feed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedRepository extends JpaRepository<Feed, Long> {

}
