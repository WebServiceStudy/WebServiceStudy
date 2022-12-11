package com.wss.webservicestudy.web.home.controller;

import com.wss.webservicestudy.web.common.security.domain.SessionUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    private final HttpSession httpSession;

    public HomeController(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }
    @GetMapping("/success")
    public String oauthSuccess(ModelAndView mv) {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if (user != null) {
            mv.addObject("user", user);
        }
        return "success";
    }
}
