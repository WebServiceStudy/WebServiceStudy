package com.wss.webservicestudy.web.feed.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags="게시판")
@RestController
public class FeedController {

    @ApiOperation(value="게시판 페이지 테스트", notes="게시판 페이지 테스트를 하기위한 내용을 적용한다.")
    @RequestMapping("/feed")
    public String home() {
        return "move feed";
    }
}
