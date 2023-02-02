package com.wss.webservicestudy.web.home.controller;

import com.wss.webservicestudy.web.common.ApiResponse;
import com.wss.webservicestudy.web.feed.dto.FeedRespDto;
import com.wss.webservicestudy.web.feed.service.FeedService;
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
    private final HttpSession httpSession;

    private final HomeService homeService;
    private final FeedService feedService;

    public HomeController(HttpSession httpSession, HomeService homeService, FeedService feedService) {
        this.httpSession = httpSession;
        this.homeService = homeService;
        this.feedService = feedService;
    }

    @ApiOperation(value = "피드 목록 조회", notes = "피드 목록 조회")
    @GetMapping("")
    public ApiResponse<List<FeedRespDto>> feeds() {
        return ApiResponse.ok(feedService.findAllDesc());
    }

//    @GetMapping("/")
//    public String home() {
//        return "index";
//    }
//    @GetMapping("/success")
//    public String oauthSuccess(ModelAndView mv) {
//        SessionUser user = (SessionUser) httpSession.getAttribute("user");
//        if (user != null) {
//            mv.addObject("user", user);
//        }
//        return "success";
//    }
//
//    @GetMapping("/test/exeption")
//    public void testtt() {
//        int a = 1;
//        throw new NullPointerException();
//    }

    @GetMapping("/test/exeption")
    public void testtt() {
        int a = 1;
        throw new NullPointerException();
    }

    @GetMapping("/test/ex-list")
    public ApiResponse<List<String>> exList() {
        List<String> ex = new ArrayList<>();
        ex.add("진홍");
        ex.add("상현");
        ex.add("지은");
        ex.add("우진");
        ex.add("유미");

        return ApiResponse.ok(ex);
    }

    @GetMapping("/test/ex-map")
    public ApiResponse<Map<String, String>> exMap() {
        Map<String, String> ex = new HashMap<String, String>();
        ex.put("1", "유미");
        ex.put("2", "우진");
        ex.put("3", "지은");
        ex.put("4", "상현");
        ex.put("", "진홍");

        return ApiResponse.ok(ex);
    }

}
