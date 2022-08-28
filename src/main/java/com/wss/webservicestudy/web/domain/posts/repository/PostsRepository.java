package com.wss.webservicestudy.web.domain.posts.repository;

import com.wss.webservicestudy.web.domain.posts.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {

}