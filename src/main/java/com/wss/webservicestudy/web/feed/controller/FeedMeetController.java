package com.wss.webservicestudy.web.feed.controller;

import com.wss.webservicestudy.web.feed.entity.FeedMeet;
import com.wss.webservicestudy.web.feed.service.FeedMeetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags="FeedMeet")
@RestController
@RequiredArgsConstructor
@RequestMapping("/feedmeet")
public class FeedMeetController {
    private final FeedMeetService feedMeetService;

    @ApiOperation(value = "FeedMeet create", notes = "사용자가 피드에 참여 신청한다.")
    @PostMapping("")
    public Long create(@RequestParam(name="feed")final Long feedId){
        return feedMeetService.create(feedId).getId();
    }

    @ApiOperation(value = "FeedMeet read", notes = "FeedMeet 정보를 찾는다.")
    @GetMapping("/{feedMeet}")
    public Long read(@PathVariable(name="feedMeet")final Long feedMeetId){
        return feedMeetService.read(feedMeetId).getId();
    }

    @ApiOperation(value = "FeedMeet update", notes = "작성자가 신청자를 승인한다.")
    @PutMapping("/{feedMeet}")
    public Long update(@PathVariable(name="feedMeet")final Long feedMeetId){
        return feedMeetService.update(feedMeetId).getId();
    }

    @ApiOperation(value = "FeedMeet delete", notes = "작성자가 신청자를 거절한다.")
    @DeleteMapping("/{feedMeet}")
    public Long delete(@PathVariable(name="feedMeet")final Long feedMeetId){
        feedMeetService.delete(feedMeetId);
        return feedMeetId;
    }
}
