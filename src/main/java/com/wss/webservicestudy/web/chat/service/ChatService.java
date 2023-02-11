package com.wss.webservicestudy.web.chat.service;

import com.wss.webservicestudy.web.chat.dto.ChatDto;
import com.wss.webservicestudy.web.chat.entity.Chat;
import com.wss.webservicestudy.web.chat.entity.Room;
import com.wss.webservicestudy.web.chat.repository.ChatRepository;
import com.wss.webservicestudy.web.chat.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ChatService {

    private final ChatRepository chatRepository;
    private final RoomRepository roomRepository;


    @Transactional
    public Chat create(ChatDto chatDto) {
        Room room = roomRepository.findById(chatDto.getRoomId()).orElseThrow(() -> new IllegalArgumentException("room 없음. id = " + chatDto.getRoomId()));

        return chatRepository.save(Chat.builder()
                .room(room)
                .sender(chatDto.getSender())
                .message(chatDto.getMessage())
                .build());
    }
}
