package com.wss.webservicestudy.web.domain.posts.service;

import com.wss.webservicestudy.web.domain.posts.dto.PostsResponseDto;
import com.wss.webservicestudy.web.domain.posts.dto.PostsSaveRequestDto;
import com.wss.webservicestudy.web.domain.posts.dto.PostsUpdateRequestDto;
import com.wss.webservicestudy.web.domain.posts.entity.Posts;
import com.wss.webservicestudy.web.domain.posts.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id ));

        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById (Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        return new PostsResponseDto(entity);
    }
}
