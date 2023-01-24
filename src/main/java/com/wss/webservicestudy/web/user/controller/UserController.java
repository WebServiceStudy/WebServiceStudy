package com.wss.webservicestudy.web.user.controller;

import com.wss.webservicestudy.web.common.ApiResponse;
import com.wss.webservicestudy.web.common.security.jwt.dto.TokenInfo;
import com.wss.webservicestudy.web.common.security.jwt.dto.TokenRequestDto;
import com.wss.webservicestudy.web.common.util.SecurityUtil;
import com.wss.webservicestudy.web.user.dto.UserLoginReqDto;
import com.wss.webservicestudy.web.user.dto.UserRespDto;
import com.wss.webservicestudy.web.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("signup")
    public ApiResponse<UserRespDto> signup(@RequestBody UserLoginReqDto reqDto) {
        return ApiResponse.ok(userService.signup(reqDto));
    }

    @PostMapping("login")
    public ApiResponse<TokenInfo> login(@RequestBody UserLoginReqDto reqDto) {
        return ApiResponse.ok(userService.login(reqDto));
    }

    @PostMapping("reissue")
    public ApiResponse<TokenInfo> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        log.info("============재발급============");
        return ApiResponse.ok(userService.reissue(tokenRequestDto));
    }
}
