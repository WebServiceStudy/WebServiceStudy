package com.wss.webservicestudy.web.home.controller;

import com.wss.webservicestudy.web.common.ApiResponse;
import com.wss.webservicestudy.web.common.security.domain.SessionUser;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/home")
public class HomeController {
    private final HttpSession httpSession;

    public HomeController(HttpSession httpSession) {
        this.httpSession = httpSession;
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

    @GetMapping("/test")
    public ApiResponse<List<String>> listTest() {
        List<String> testStrList = new ArrayList<>();
        testStrList.add("test1");
        testStrList.add("test2");
        testStrList.add("test3");

        return ApiResponse.ok(testStrList);
    }
}
