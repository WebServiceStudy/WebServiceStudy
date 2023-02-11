package com.wss.webservicestudy.web.chat.controller;

import com.wss.webservicestudy.web.chat.dto.ChatDto;
import com.wss.webservicestudy.web.chat.entity.Chat;
import com.wss.webservicestudy.web.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ChatController {

    private final ChatService chatService;

    @MessageMapping("/{roomId}")    // 여기로 전송되면 메서드 호출 -> WebSocketConfig의 prefix와 합쳐짐 (/send/{roomId}의 형식
    @SendTo("/room/{roomId}")
    public ChatDto sendChat(@DestinationVariable Long roomId, ChatDto chatDto) {
        Chat chat = chatService.create(chatDto);
        return ChatDto.builder()
                .roomId(roomId)
                .sender(chat.getSender())
                .message(chat.getMessage())
                .build();
    }
}
