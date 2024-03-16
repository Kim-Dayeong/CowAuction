package com.hoarse.auction.web.repository.chat;

import com.hoarse.auction.web.entity.chat.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
}
