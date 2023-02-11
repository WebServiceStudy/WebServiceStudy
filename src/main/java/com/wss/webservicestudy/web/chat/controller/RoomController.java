package com.wss.webservicestudy.web.chat.controller;

import com.wss.webservicestudy.web.chat.dto.RoomCreateDto;
import com.wss.webservicestudy.web.chat.dto.RoomResDto;
import com.wss.webservicestudy.web.chat.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/room")
@RestController
public class RoomController {

    private final RoomService roomService;

    @PostMapping("")
    public RoomResDto create(@RequestBody RoomCreateDto roomCreateDto) {
        log.info(roomCreateDto.getName());
        return roomService.create(roomCreateDto);
    }

    @GetMapping("")
    public List<RoomResDto> getRoomList() {
        return roomService.findAllRoom();
    }
}
