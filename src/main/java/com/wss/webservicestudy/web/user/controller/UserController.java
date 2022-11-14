package com.wss.webservicestudy.web.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Api(tags = "유저")
@RestController
public class UserController {
    @ApiOperation(value="초기테스트")
    @RequestMapping("/")
    public String home() {
        return "hello World";
    }
}
