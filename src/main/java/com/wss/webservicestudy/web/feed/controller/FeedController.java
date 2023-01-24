package com.wss.webservicestudy.web.feed.controller;

import com.wss.webservicestudy.web.common.ApiResponse;
import com.wss.webservicestudy.web.common.ApiResponse;
import com.wss.webservicestudy.web.common.util.SecurityUtil;
import com.wss.webservicestudy.web.feed.dto.CreateFeedDto;
import com.wss.webservicestudy.web.feed.dto.FeedRespDto;
import com.wss.webservicestudy.web.feed.dto.UpdateFeedDto;
import com.wss.webservicestudy.web.feed.service.FeedService;
import com.wss.webservicestudy.web.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
import java.util.List;

@Api(tags="게시판")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/feed")
public class FeedController {
    private final FeedService feedService;

    private final UserService userService;

    @ApiOperation(value = "피드 목록 조회", notes = "피드 목록 조회")
    @GetMapping("")
    public ApiResponse<List<FeedRespDto>> feeds() {
        return ApiResponse.ok(feedService.findAllDesc());
    }

    @ApiOperation(value = "피드 조회", notes = "피드 조회")
    @GetMapping("/{feed}")
    public ApiResponse<FeedRespDto> read(@PathVariable("feed") Long feedId) {
        return ApiResponse.ok(feedService.findRespById(feedId));
    }

    @ApiOperation(value = "피드 생성", notes = "피드 생성")
    @PostMapping("")
    public ApiResponse<Long> create(@RequestBody @Valid CreateFeedDto feedDto,
                                    BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return null;
        }
        return ApiResponse.ok(feedService.create(feedDto).getId());
        //return ApiResponse.ok(feedService.create(feedDto).getId());
    }

    @ApiOperation(value = "피드 수정", notes = "피드 수정")
    @PutMapping("/{feed}")
    public ApiResponse<Long> update(@PathVariable(name="feed")final Long feedId,
                                    @RequestBody @Valid UpdateFeedDto feedDto,
                                    BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return null;
        }
        return ApiResponse.ok(feedService.update(feedId, feedDto).getId());
    }

    @ApiOperation(value = "피드 삭제", notes = "피드 삭제")
    @DeleteMapping("/{feed}")
    public ApiResponse<Long> delete(@PathVariable(name = "feed") Long feedId) {
        return ApiResponse.ok(feedService.delete(feedId));
    }
}
