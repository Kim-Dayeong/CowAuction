package com.hoarse.auction.web.controller.chat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoarse.auction.web.entity.chat.ChatMessage;
import com.hoarse.auction.web.repository.chat.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@RestController
@RequiredArgsConstructor
public class ChatMessageController {

    private final SimpMessageSendingOperations sendingOperations;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final ChatMessageRepository chatMessageRepository;


    @MessageMapping("/chat/message")
    public void enter(ChatMessage message) {
        if (ChatMessage.MessageType.ENTER.equals(message.getType())) {
            message.setMessage(message.getSender() + "님이 입장하였습니다.");
        }
        // 채팅 저장
        // 일단 채팅 받아오기
        System.out.println(message.getMessage());

        sendingOperations.convertAndSend("/topic/chat/room/" + message.getRoomId(), message);


        // 받아온 채팅 레디스에 저장해서 총 10개의 요소가 저장되면 한번 db에 저장하기

        try (Jedis jedis = new Jedis("localhost", 6379)) {
            jedis.lpush(message.getRoomId(),convertToJson(message));

            // 채팅 갯수 확인
            Long chatCount = jedis.llen(message.getRoomId());
            if(chatCount >= 10){
                saveChatMessageToDB(Long.valueOf(message.getRoomId()));
                jedis.del(message.getRoomId()); // 레디스에 저장됐던 메시지 삭제
            }
        }
    }
    public String convertToJson(ChatMessage message) {
        try {
            return objectMapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ""; // 변환 실패 시 빈 문자열 반환
        }
    }

    public void saveChatMessageToDB(Long roomId) {
        try (Jedis jedis = new Jedis("localhost", 6379)) {
            List<String> messages = jedis.lrange(String.valueOf(roomId), 0, -1);
            for (String json : messages) {
                try {
                    ChatMessage chatMessage = objectMapper.readValue(json, ChatMessage.class);
                    chatMessageRepository.save(chatMessage);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
            }
        }
    }


}


