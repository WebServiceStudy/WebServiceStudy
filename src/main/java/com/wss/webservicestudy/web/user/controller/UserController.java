package com.wss.webservicestudy.web.user.controller;

import com.wss.webservicestudy.web.common.security.core.userdetails.CustomOAuth2UserService;
import com.wss.webservicestudy.web.user.domain.User;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private final CustomOAuth2UserService customOAuth2UserService;

    public UserController(CustomOAuth2UserService customOAuth2UserService) {
        this.customOAuth2UserService = customOAuth2UserService;
    }

    @GetMapping("/loginForm")
    public String loginForm(){
        return "login";
    }

//    @PostMapping("/join")
//    public String join(@ModelAttribute User user){
//        return "redirect:/loginFrom";
//    }
}
