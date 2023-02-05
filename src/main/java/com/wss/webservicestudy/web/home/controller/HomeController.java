package com.wss.webservicestudy.web.home.controller;

import com.wss.webservicestudy.web.common.ApiResponse;
import com.wss.webservicestudy.web.feed.dto.FeedsRespDto;
import com.wss.webservicestudy.web.feed.service.FeedService;
import com.wss.webservicestudy.web.feed.dto.FeedRespDto;
import com.wss.webservicestudy.web.home.service.HomeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/home")
public class HomeController {

    private final HomeService homeService;
    private final FeedService feedService;
    private final HttpSession httpSession;

    public HomeController(HttpSession httpSession, HomeService homeService, FeedService feedService) {
        this.httpSession = httpSession;
        this.homeService = homeService;
        this.feedService = feedService;
    }

    @ApiOperation(value = "피드 목록 조회", notes = "피드 목록 조회")
    @GetMapping("")
    public ApiResponse<List<FeedsRespDto>> feeds() {
        return ApiResponse.ok(feedService.findAllDesc());
    }
}
