package com.hoarse.auction.web.controller.chat;

import com.hoarse.auction.web.entity.chat.ChatMessage;
import com.hoarse.auction.web.service.chat.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final SimpMessageSendingOperations sendingOperations;


    @MessageMapping("/chat/message")
    public void enter(ChatMessage message) {
        if (ChatMessage.MessageType.ENTER.equals(message.getType())) {
            message.setMessage(message.getSender()+"님이 입장하였습니다.");
        }
        // 채팅 저장
        // 일단 채팅 받아오기
        System.out.println(message.getMessage());

        sendingOperations.convertAndSend("/topic/chat/room/"+message.getRoomId(),message);

        // Redis 서버에 연결
        Jedis jedis = new Jedis("localhost", 6379);

        try {
            // Redis에 String 저장
            String key = "test";
            String value = message.getMessage();
            jedis.set(key, value);

            // Redis에서 String 읽기
            String retrievedValue = jedis.get(key);
            System.out.println("Retrieved value from Redis: " + retrievedValue);
        } finally {
            // 연결 닫기
            jedis.close();
        }
    }
    }

