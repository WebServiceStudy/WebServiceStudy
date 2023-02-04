package com.wss.webservicestudy.web.user.controller;

import com.wss.webservicestudy.web.common.ApiResponse;
import com.wss.webservicestudy.web.common.util.SecurityUtil;
import com.wss.webservicestudy.web.user.dto.UserRespDto;
import com.wss.webservicestudy.web.user.service.UserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Api(tags = "유저")
@Slf4j
@RequestMapping("/api/user")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

<<<<<<< HEAD
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
=======
    @GetMapping("/info")
    public ApiResponse<UserRespDto> getUserInfo() {
        return ApiResponse.ok(userService.findUserByEmail(SecurityUtil.getCurrentMember()));
>>>>>>> 3455069083ced3e77c241b0d551ba8a5d65eca1b
    }
}
