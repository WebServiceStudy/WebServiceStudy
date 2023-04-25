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

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Api(tags="게시판")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/feed")
public class FeedController {
    private final FeedService feedService;

    @ApiOperation(value = "사용자 작성 피드 조회", notes = "사용자 작성 피드 조회")
    @GetMapping("/writer")
    public ApiResponse<List<FeedsRespDto>> userFeeds(@PageableDefault(size = 5) Pageable pageable) {
        return ApiResponse.ok(feedService.findUserFeeds(pageable));
    }

    @ApiOperation(value = "사용자 참여 요청 피드 조회", notes = "사용자 참여 요청 피드 조회")
    @GetMapping("/applied")
    public ApiResponse<List<FeedsRespDto>> userAppliedFeeds(@PageableDefault(size = 5) Pageable pageable) {
        return ApiResponse.ok(feedService.findUserAppliedFeeds(pageable));
    }

    // TODO: 본인 글이면 신청한 인원 현재 정보 보여주기 (프사, 닉네임, 성별, id, ParticipantStatus)
    // TODO: 본인 글 아니면 해당 글에대한 로그인유저 FeedMeet 상태 보여주기 (ParticipantStatus)
    @ApiOperation(value = "피드 조회", notes = "피드 조회")
    @GetMapping("/{feed}")
    public ApiResponse<FeedRespDto> read(@PathVariable("feed") Long feedId) {
        return ApiResponse.ok(feedService.findRespById(feedId));
    }

    @ApiOperation(value = "피드 상태값 조회", notes = "피드 상태값 조회")
    @GetMapping("/{feed}/status")
    public ApiResponse<FeedStatus> readFeedStatus(@PathVariable("feed") Long feedId){
        return ApiResponse.ok(feedService.findFeedStatus(feedId));
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

    @ApiOperation(value = "피드 상태 수정", notes = "작성자가 feed status 상태를 바꾼다.")
    @PutMapping("/{feed}/status")
    public ApiResponse<Long> update(@PathVariable(name = "feed")final Long feedId, @RequestBody FeedStatus feedStatus){
        return ApiResponse.ok(feedService.updateStatus(feedId, feedStatus).getId());
    }

    // TODO: 인원수 변경시 현재 승인된 인원 수 보다 적으면 안됨.
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
