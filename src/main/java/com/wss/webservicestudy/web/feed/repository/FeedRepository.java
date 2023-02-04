package com.wss.webservicestudy.web.feed.repository;

import com.wss.webservicestudy.web.feed.entity.Feed;
import com.wss.webservicestudy.web.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedRepository extends JpaRepository<Feed, Long> {

    List<Feed> findAllByOrderByIdDesc();

    List<Feed> findAllByWriter(User writer);

    Feed findTop1ByOrderByIdDesc();
}
