package com.wss.webservicestudy.web.feed.controller;

import com.wss.webservicestudy.web.feed.entity.Feed;
import com.wss.webservicestudy.web.feed.service.FeedService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Api(tags="게시판")
@RestController
public class FeedController {

    @Autowired
    FeedService feedService;

    @ApiOperation(value="게시판 페이지 테스트", notes="게시판 페이지 테스트를 하기위한 내용을 적용한다.")
    @RequestMapping("/feed")
    public String home() {
        return "move feed";
    }

    @RequestMapping("/feed/{feedId}/detail")
    public String readFeed(@PathVariable("feedId") Long feedId, ModelAndView mv) {
        Feed feed = feedService.findOne(feedId);
        mv.addObject("feed", feed);
        return "feed/feed";
    }
}
