package com.wss.webservicestudy.web.home.controller;

import com.wss.webservicestudy.web.common.ApiResponse;
import com.wss.webservicestudy.web.feed.dto.FeedRespDto;
import com.wss.webservicestudy.web.home.service.HomeService;
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


    @GetMapping("/hello")
    public ApiResponse<String> hello() {
        return ApiResponse.ok("wss home api");
    }

//    @GetMapping("/feed")
//    public ApiResponse<List<FeedRespDto>> getFeedList() {
//
//    }
}
