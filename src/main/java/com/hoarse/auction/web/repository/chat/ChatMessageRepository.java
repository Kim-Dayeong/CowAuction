package com.hoarse.auction.web.repository.chat;

import com.hoarse.auction.web.entity.chat.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    List<ChatMessage> findTop50ByRoomIdOrderByTimeDesc(String roomId);

}
