package com.wss.webservicestudy.web.user.controller;

import com.wss.webservicestudy.web.common.ApiResponse;
import com.wss.webservicestudy.web.common.security.jwt.dto.TokenInfo;
import com.wss.webservicestudy.web.common.security.jwt.dto.TokenRequestDto;
import com.wss.webservicestudy.web.user.dto.UserLoginReqDto;
import com.wss.webservicestudy.web.user.dto.UserRespDto;
import com.wss.webservicestudy.web.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "유저")
@Slf4j
@RequestMapping("/api/user/")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "페이지 테스트", notes = "유저 페이지 테스트를 하기위한 내용을 적용한다.")
    @RequestMapping("/user")
    public String home() {
        return "hello World";
    }

    @ApiOperation(value = "회원 가입", notes = "email, password 기반으로 회원가입")
    @PostMapping("signup")
    public ApiResponse<UserRespDto> signup(@RequestBody UserLoginReqDto reqDto) {
        return ApiResponse.ok(userService.signup(reqDto));
    }
    @ApiOperation(value = "로그인", notes = "email, password를 통한 로그인")
    @PostMapping("login")
    public ApiResponse<TokenInfo> login(@RequestBody UserLoginReqDto reqDto) {
        return ApiResponse.ok(userService.login(reqDto));
    }

    @ApiOperation(value = "jwt 토큰 재발급", notes = "토큰 재발급")
    @PostMapping("reissue")
    public ApiResponse<TokenInfo> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return ApiResponse.ok(userService.reissue(tokenRequestDto));
    }

}
