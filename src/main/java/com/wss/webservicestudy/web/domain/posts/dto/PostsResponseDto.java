package com.wss.webservicestudy.web.domain.posts.dto;

import com.wss.webservicestudy.web.domain.posts.entity.Posts;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostsResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
