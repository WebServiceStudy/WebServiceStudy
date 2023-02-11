package com.wss.webservicestudy.web.chat.service;

import com.wss.webservicestudy.web.chat.dto.RoomCreateDto;
import com.wss.webservicestudy.web.chat.dto.RoomResDto;
import com.wss.webservicestudy.web.chat.entity.Room;
import com.wss.webservicestudy.web.chat.repository.RoomRepository;
import com.wss.webservicestudy.web.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RoomService {
    private final RoomRepository roomRepository;

    private final UserService userService;

    @Transactional
    public RoomResDto create(RoomCreateDto roomCreateDto) {
        return roomRepository.save(roomCreateDto.toEntity()).toDto();
    }

    @Transactional(readOnly = true)
    public List<RoomResDto> findAllRoom() {
        return roomRepository.findAll()
                .stream()
                .map(Room::toDto)
                .collect(Collectors.toList());
    }
}
