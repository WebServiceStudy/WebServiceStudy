package com.wss.webservicestudy.web.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Api(tags="유저")
@RestController
public class UserRestController {
    @ApiOperation(value="페이지 테스트", notes="유저 페이지 테스트를 하기위한 내용을 적용한다.")
    @RequestMapping("/user")
    public String home() {
        return "hello World";
    }
}
