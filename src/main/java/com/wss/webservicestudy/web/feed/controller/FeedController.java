package com.wss.webservicestudy.web.feed.controller;

import com.wss.webservicestudy.web.common.ApiResponse;
import com.wss.webservicestudy.web.feed.dto.CreateFeedDto;
import com.wss.webservicestudy.web.feed.dto.FeedRespDto;
import com.wss.webservicestudy.web.feed.dto.FeedsRespDto;
import com.wss.webservicestudy.web.feed.dto.UpdateFeedDto;
import com.wss.webservicestudy.web.feed.service.FeedService;
import com.wss.webservicestudy.web.feed.type.FeedStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

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

    @ApiOperation(value = "사용자 작성 피드 조회", notes = "사용자 작성 피드 조회")
    @GetMapping("/writer")
    public ApiResponse<List<FeedsRespDto>> userFeeds() {
        List<FeedsRespDto> feeds = feedService.findUserFeeds();
        return ApiResponse.ok(feeds);
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

    @ApiOperation(value = "피드 상태 수정", notes = "작성자가 feed status 상태를 바꾼다.")
    @PutMapping("/{feed}/status")
    public ApiResponse<Long> update(@PathVariable(name = "feed")final Long feedId, @RequestBody FeedStatus feedStatus){
        return ApiResponse.ok(feedService.updateStatus(feedId, feedStatus).getId());
    }

    @ApiOperation(value = "피드 삭제", notes = "피드 삭제")
    @DeleteMapping("/{feed}")
    public ApiResponse<Long> delete(@PathVariable(name = "feed") Long feedId) {
        return ApiResponse.ok(feedService.delete(feedId));
    }
}
