package com.wss.webservicestudy.web.feed.repository;

import com.wss.webservicestudy.web.feed.entity.Feed;
import com.wss.webservicestudy.web.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedRepository extends JpaRepository<Feed, Long> {

    @Query("SELECT f FROM Feed f" +
            " INNER JOIN FETCH f.writer")
    List<Feed> findAllByOrderByIdDesc();

    List<Feed> findAllByWriter(User writer);

    List<Feed> findAllByFeedMeets_User(User user);

    Feed findTop1ByOrderByIdDesc();

    @Query("SELECT f FROM Feed f" +
            " INNER JOIN FETCH f.feedMeets fm" +
            " INNER JOIN FETCH fm.user" +
            " WHERE f.id = :feedId")
    Feed findByIdWithFeedMeets(@Param("feedId") long feedId);
}
