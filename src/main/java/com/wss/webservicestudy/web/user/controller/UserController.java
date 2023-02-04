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
@RequestMapping("/api/user")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/info")
    public ApiResponse<UserRespDto> getUserInfo() {
        return ApiResponse.ok(userService.findUserByEmail(SecurityUtil.getCurrentMember()));
    }
}
