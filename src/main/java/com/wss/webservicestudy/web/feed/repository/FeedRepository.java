package com.wss.webservicestudy.web.feed.repository;

import com.wss.webservicestudy.web.feed.entity.Feed;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class FeedRepository {

    @PersistenceContext
    EntityManager em;

    public Feed findOne(Long feedId) {
        return em.find(Feed.class, feedId);
    }

    public void save(Feed feed) {
        em.persist(feed);
    }
}
