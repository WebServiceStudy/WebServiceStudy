package com.wss.webservicestudy.web.chat.entity;

import com.wss.webservicestudy.web.chat.dto.RoomResDto;
import com.wss.webservicestudy.web.user.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Room {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Room(String name) {
        this.name = name;
    }

    public RoomResDto toDto() {
        return RoomResDto.builder()
                .name(this.name)
                .build();
    }
}
