package com.hoarse.auction.web.service.chat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoarse.auction.web.dto.chat.ChatMessageDto;
import com.hoarse.auction.web.entity.chat.ChatMessage;
import com.hoarse.auction.web.repository.chat.ChatMessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final ObjectMapper objectMapper;

    // 채팅 저장

    // 받아온 채팅 레디스에 저장해서 총 10개의 요소가 저장되면 한번 db에 저장하기
    public void saveChat(ChatMessage message) {

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


    //채팅 불러오기
    public List<ChatMessageDto> loadChat(String roomId) {
        List<ChatMessageDto> messageList = new ArrayList<>();

        //redis에서 해당 채팅방 메시지 10개 가져오기 + 만약 redis에 저장된 채팅있으면 밑에 추가로 가져오기
        List<ChatMessage> chatMessageList = chatMessageRepository.findTop50ByRoomIdOrderByTimeDesc(roomId);

        for(ChatMessage chatMessage : chatMessageList){ // chatnessagedto 변환
    messageList.add(new ChatMessageDto(chatMessage));
        }

        try (Jedis jedis = new Jedis("localhost", 6379)) {
            List<String> redisMessages = jedis.lrange(roomId, 0, 10); // redis에서 추가로 저장된 메시지 가져오기
            if (redisMessages != null && !redisMessages.isEmpty()) {
                for (String redisMessage : redisMessages) {
                    try {
                        ChatMessage chatMessage = objectMapper.readValue(redisMessage, ChatMessage.class);
                        messageList.add(new ChatMessageDto(chatMessage));

                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                        System.out.println("json파싱 에러" + e.getMessage());

                    }
                }

            }

        }
        return messageList;

    }
}


