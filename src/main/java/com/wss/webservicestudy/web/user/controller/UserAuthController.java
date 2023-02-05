package com.wss.webservicestudy.web.user.controller;

import com.wss.webservicestudy.web.common.ApiResponse;
import com.wss.webservicestudy.web.common.security.jwt.dto.TokenInfo;
import com.wss.webservicestudy.web.common.security.jwt.dto.TokenRequestDto;
import com.wss.webservicestudy.web.user.dto.UserLoginReqDto;
import com.wss.webservicestudy.web.user.dto.UserRespDto;
import com.wss.webservicestudy.web.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/auth")
public class UserAuthController {
    private final UserService userService;

    public UserAuthController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "회원가입", notes = "회원가입")
    @PostMapping("/signup")
    public ApiResponse<UserRespDto> signup(@RequestBody UserLoginReqDto reqDto) {
        return ApiResponse.ok(userService.signup(reqDto));
    }

    @ApiOperation(value = "일반 로그인", notes = "ID, Password 로그인")
    @PostMapping("/login")
    public ApiResponse<TokenInfo> login(@RequestBody UserLoginReqDto reqDto, HttpServletResponse response) {
        return ApiResponse.ok(userService.login(reqDto, response));
    }

    @ApiOperation(value = "토큰 재발급", notes = "acToken, rfToken 재발급")
    @PostMapping("/reissue")
    public ApiResponse<TokenInfo> reissue(@RequestBody TokenRequestDto tokenRequestDto, HttpServletRequest request, HttpServletResponse response) {
        return ApiResponse.ok(userService.reissue(tokenRequestDto, request, response));
    }
}
