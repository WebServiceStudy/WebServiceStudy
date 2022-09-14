package com.wss.webservicestudy.web.domain.posts.controller;

import com.wss.webservicestudy.web.domain.posts.dto.PostsResponseDto;
import com.wss.webservicestudy.web.domain.posts.dto.PostsSaveRequestDto;
import com.wss.webservicestudy.web.domain.posts.dto.PostsUpdateRequestDto;
import com.wss.webservicestudy.web.domain.posts.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping()
    public PostsResponseDto finById(@PathVariable long id) {
        return postsService.findById(id);
    }
}
