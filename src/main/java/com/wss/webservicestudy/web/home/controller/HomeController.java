package com.wss.webservicestudy.web.home.controller;

import com.wss.webservicestudy.web.common.ApiResponse;
import com.wss.webservicestudy.web.feed.dto.FeedsRespDto;
import com.wss.webservicestudy.web.feed.service.FeedService;
import com.wss.webservicestudy.web.home.service.HomeService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/home")
public class HomeController {

    private final HomeService homeService;
    private final FeedService feedService;

    public HomeController(HomeService homeService, FeedService feedService) {
        this.homeService = homeService;
        this.feedService = feedService;
    }

    @ApiOperation(value = "피드 목록 조회", notes = "피드 목록 조회")
    @GetMapping("/feeds")
    public ApiResponse<List<FeedsRespDto>> feeds(@PageableDefault(size = 5) Pageable pageable) {
        return ApiResponse.ok(feedService.findAllDesc(pageable));
    }
}
