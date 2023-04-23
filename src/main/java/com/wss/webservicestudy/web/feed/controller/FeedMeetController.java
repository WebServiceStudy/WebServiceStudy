package com.wss.webservicestudy.web.feed.controller;

import com.wss.webservicestudy.web.common.ApiResponse;
import com.wss.webservicestudy.web.feed.service.FeedMeetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags="FeedMeet")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/feed/feedmeet")
public class FeedMeetController {
    private final FeedMeetService feedMeetService;

    @ApiOperation(value = "FeedMeet apply", notes = "사용자가 피드에 참여 신청한다.")
    @PostMapping()
    public ApiResponse<Long> apply(@RequestParam(name="feed") final Long feedId){
        return ApiResponse.ok(feedMeetService.apply(feedId).getId());
    }

    @ApiOperation(value = "FeedMeet read", notes = "FeedMeet 정보를 찾는다.")
    @GetMapping("/{feedMeet}")
    public ApiResponse<Long> read(@PathVariable(name="feedMeet") final Long feedMeetId){
        return ApiResponse.ok(feedMeetService.read(feedMeetId).getId());
    }

    @ApiOperation(value = "FeedMeet update", notes = "작성자가 신청자를 승인한다.")
    @PutMapping("/{feedMeet}/approve")
    public ApiResponse<Long> approve(@PathVariable(name="feedMeet") final Long feedMeetId){
        return ApiResponse.ok(feedMeetService.approve(feedMeetId).getId());
    }

    @ApiOperation(value = "FeedMeet update", notes = "신청자가 참여를 취소한다.")
    @PutMapping("/{feedMeet}/cancel")
    public ApiResponse<Long> cancel(@PathVariable(name="feedMeet") final Long feedMeetId){
        return ApiResponse.ok(feedMeetService.cancel(feedMeetId).getId());
    }

    @ApiOperation(value = "FeedMeet update", notes = "작성자가 신청자를 거절한다.")
    @PutMapping("/{feedMeet}/refusal")
    public ApiResponse<Long> refusal(@PathVariable(name="feedMeet") final Long feedMeetId){
        return ApiResponse.ok(feedMeetService.refusal(feedMeetId).getId());
    }

    @ApiOperation(value = "FeedMeet delete", notes = "작성자가 신청자를 거절한다.")
    @DeleteMapping("/{feedMeet}")
    public ApiResponse<Long> delete(@PathVariable(name="feedMeet") final Long feedMeetId){
        feedMeetService.delete(feedMeetId);
        return ApiResponse.ok(feedMeetId);
    }
}
