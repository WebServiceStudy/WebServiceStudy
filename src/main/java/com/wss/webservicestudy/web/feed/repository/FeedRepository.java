package com.wss.webservicestudy.web.feed.repository;

import com.wss.webservicestudy.web.feed.entity.Feed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedRepository extends JpaRepository<Feed, Long> {

    List<Feed> findAllByOrderByIdDesc();

    Feed findTop1ByOrderByIdDesc();
}
