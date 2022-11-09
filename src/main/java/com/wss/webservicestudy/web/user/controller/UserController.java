package com.wss.webservicestudy.web.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @RequestMapping("/")
    public String home() {
        return "hello World";
    }
}
