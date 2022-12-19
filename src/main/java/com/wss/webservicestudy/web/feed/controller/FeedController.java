package com.wss.webservicestudy.web.feed.controller;

import com.wss.webservicestudy.web.feed.dto.CreateFeedDto;
import com.wss.webservicestudy.web.feed.service.FeedService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags="게시판")
@RestController
@RequestMapping("/feed")
public class FeedController {
    private final FeedService feedService;

    FeedController(FeedService feedService){
        this.feedService = feedService;
    }

    @ApiOperation(value="게시판 페이지 테스트", notes="게시판 페이지 테스트를 하기위한 내용을 적용한다.")
    @RequestMapping("")
    public String home() {
        return "move feed";
    }

    @ApiOperation(value = "피드 생성", notes = "피드 생성")
    @PostMapping("")
    public Long create(@RequestBody @Valid CreateFeedDto feedDto){
        return feedService.create(feedDto);
    }
}
