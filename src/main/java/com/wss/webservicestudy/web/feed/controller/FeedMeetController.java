package com.wss.webservicestudy.web.feed.controller;

import com.wss.webservicestudy.web.common.ApiResponse;
import com.wss.webservicestudy.web.feed.service.FeedMeetService;
import com.wss.webservicestudy.web.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@Api(tags="FeedMeet")
@RestController
@RequiredArgsConstructor
@RequestMapping("/feed/feedmeet")
public class FeedMeetController {
    private final FeedMeetService feedMeetService;

    private final UserService userService;

    @ApiOperation(value = "FeedMeet create", notes = "사용자가 피드에 참여 신청한다.")
    @PostMapping("")
    public ApiResponse<Long> create(@RequestParam(name="feed") final Long feedId,
                                    @AuthenticationPrincipal User userAuth){
        return ApiResponse.ok(feedMeetService.create(feedId, userAuth.getUsername()).getId());
        //return ApiResponse.ok(feedMeetService.create(feedId).getId());
    }

    @ApiOperation(value = "FeedMeet read", notes = "FeedMeet 정보를 찾는다.")
    @GetMapping("/{feedMeet}")
    public ApiResponse<Long> read(@PathVariable(name="feedMeet") final Long feedMeetId){
        return ApiResponse.ok(feedMeetService.read(feedMeetId).getId());
    }

    @ApiOperation(value = "FeedMeet update", notes = "작성자가 신청자를 승인한다.")
    @PutMapping("/{feedMeet}")
    public ApiResponse<Long> update(@PathVariable(name="feedMeet") final Long feedMeetId,
                                    @AuthenticationPrincipal User userAuth){
        Long userId = userService.findUserIdByEmail(userAuth.getUsername());
        return ApiResponse.ok(feedMeetService.update(feedMeetId, userId).getId());
        //return ApiResponse.ok(feedMeetService.update(feedMeetId).getId());
    }

    @ApiOperation(value = "FeedMeet delete", notes = "작성자가 신청자를 거절한다.")
    @DeleteMapping("/{feedMeet}")
    public ApiResponse<Long> delete(@PathVariable(name="feedMeet") final Long feedMeetId,
                                    @AuthenticationPrincipal User userAuth){
        Long userId = userService.findUserIdByEmail(userAuth.getUsername());
        //feedMeetService.delete(feedMeetId);
        feedMeetService.delete(feedMeetId, userId);
        return ApiResponse.ok(feedMeetId);
    }
}
