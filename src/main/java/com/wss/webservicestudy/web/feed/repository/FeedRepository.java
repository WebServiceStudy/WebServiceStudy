package com.wss.webservicestudy.web.feed.repository;

import com.wss.webservicestudy.web.feed.entity.Feed;
import com.wss.webservicestudy.web.user.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedRepository extends JpaRepository<Feed, Long> {

    // Feed - N : 1 -> User(writer)
    @Query("SELECT f FROM Feed f" +
            " INNER JOIN FETCH f.writer")
    List<Feed> findAllByOrderByIdDesc(Pageable pageable);

    List<Feed> findAllByWriter(Pageable pageable, User writer);

    List<Feed> findAllByFeedMeets_User(Pageable pageable, User user);

    Feed findTop1ByOrderByIdDesc();

    // Feed <- 1 : N -> FeedMeet
    // FeedMeet - N : 1 -> User
    @Query("SELECT f FROM Feed f" +
            " INNER JOIN FETCH f.feedMeets fm" +
            " INNER JOIN FETCH fm.user" +
            " WHERE f.id = :feedId")
    Feed findByIdWithFeedMeets(@Param("feedId") long feedId);
}
