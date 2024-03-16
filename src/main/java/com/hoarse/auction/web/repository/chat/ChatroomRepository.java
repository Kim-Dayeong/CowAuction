package com.hoarse.auction.web.repository.chat;

import com.hoarse.auction.web.dto.chat.ChatRoomDto;
import com.hoarse.auction.web.entity.chat.ChatRoom;
import jakarta.annotation.PostConstruct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public interface ChatroomRepository extends JpaRepository<ChatRoom, Long> {



}
