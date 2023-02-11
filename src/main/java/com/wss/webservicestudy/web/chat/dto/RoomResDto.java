package com.wss.webservicestudy.web.chat.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoomResDto {
    private Long id;
    private String name;

    @Builder
    public RoomResDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
