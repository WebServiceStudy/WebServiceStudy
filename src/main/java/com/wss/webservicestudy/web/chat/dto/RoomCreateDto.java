package com.wss.webservicestudy.web.chat.dto;

import com.wss.webservicestudy.web.chat.entity.Room;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoomCreateDto {
    private String name;

    @Builder
    public RoomCreateDto(String name) {
        this.name = name;
    }

    public Room toEntity() {
        return Room.builder()
                .name(this.name)
                .build();
    }
}
