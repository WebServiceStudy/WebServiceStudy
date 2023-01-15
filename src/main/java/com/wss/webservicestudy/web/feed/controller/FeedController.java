package com.wss.webservicestudy.web.feed.controller;

import com.wss.webservicestudy.web.feed.dto.CreateFeedDto;
import com.wss.webservicestudy.web.feed.dto.UpdateFeedDto;
import com.wss.webservicestudy.web.feed.entity.Feed;
import com.wss.webservicestudy.web.feed.service.FeedService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags="게시판")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/feed")
public class FeedController {
    private final FeedService feedService;

    @ApiOperation(value="게시판 페이지 테스트", notes="게시판 페이지 테스트를 하기위한 내용을 적용한다.")
    @RequestMapping("")
    public String home() {
        return "move feed";
    }

    @ApiOperation(value = "피드 조회", notes = "피드 조회")
    @GetMapping("/detail/{feedId}")
    public String read(@PathVariable("feedId") Long feedId, ModelAndView mv) {
        Feed feed = feedService.findOne(feedId);
        mv.addObject("feed", feed);
        return "feed/feed";
//        return feedService.findOne(feedId);
    }

    @ApiOperation(value = "피드 생성", notes = "피드 생성")
    @PostMapping("")
    public Long create(@RequestBody @Valid CreateFeedDto feedDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return null;
        }
        return feedService.create(feedDto).getId();
    }

    @ApiOperation(value = "피드 수정", notes = "피드 수정")
    @PutMapping("/{feed}")
    public Long update(@PathVariable(name="feed")final Long feedId, @RequestBody @Valid UpdateFeedDto feedDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return null;
        }
        return feedService.update(feedId, feedDto).getId();
    }
}


