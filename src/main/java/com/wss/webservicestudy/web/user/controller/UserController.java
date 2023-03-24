package com.wss.webservicestudy.web.user.controller;

import com.wss.webservicestudy.web.common.ApiResponse;
import com.wss.webservicestudy.web.common.security.jwt.dto.TokenInfo;
import com.wss.webservicestudy.web.common.security.jwt.dto.TokenRequestDto;
import com.wss.webservicestudy.web.common.util.SecurityUtil;
import com.wss.webservicestudy.web.user.dto.UserLoginReqDto;
import com.wss.webservicestudy.web.user.dto.UserRequestDto;
import com.wss.webservicestudy.web.user.dto.UserRespDto;
import com.wss.webservicestudy.web.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Api(tags = "유저")
@Slf4j
@RequestMapping("/api/user")
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/info")
    public ApiResponse<UserRespDto> getCurrentUserInfo() {
        return ApiResponse.ok(userService.getUserInfo(SecurityUtil.getCurrentMember()));
    }

    @PostMapping("/info")
    public ApiResponse<UserRespDto> updateUserInfo(UserRequestDto updateInfo) {
        UserRespDto result = userService.updateInfo(updateInfo);
        return ApiResponse.ok(result);
    }

    @PostMapping("/profile/img")
    public ApiResponse<UserRespDto> uploadProfileImg(@RequestParam("profile") MultipartFile uploadImg) throws IOException {
        UserRespDto result = userService.updateProfile(uploadImg);

        return ApiResponse.ok(result);
    }
}
