package com.wss.webservicestudy.web.chat.entity;

import com.wss.webservicestudy.web.common.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Chat extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id")
    private Room room;
    private String sender;

    @Column(columnDefinition = "TEXT")
    private String message;

    @Builder
    public Chat(Room room, String sender, String message) {
        this.room = room;
        this.sender = sender;
        this.message = message;
    }
}
