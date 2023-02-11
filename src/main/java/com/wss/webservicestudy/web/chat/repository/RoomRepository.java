package com.wss.webservicestudy.web.chat.repository;

import com.wss.webservicestudy.web.chat.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}
