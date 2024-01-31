package com.hoarse.auction.web.controller.auction;

import com.hoarse.auction.web.entity.auction.AuctionMessage;
import com.hoarse.auction.web.entity.chat.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.concurrent.TimeUnit;

import static com.hoarse.auction.web.module.Timeout.runWithTimeout;

@RestController
@RequiredArgsConstructor
public class AuctionMessageController {
    private final SimpMessageSendingOperations sendingOperations;



    @MessageMapping("/auction/message")
    public void enter (AuctionMessage message){
        if (ChatMessage.MessageType.ENTER.equals(message.getType())) {
            message.setMessage(message.getSender() + "님이 입장하였습니다.");
        }
        // 채팅 저장
        // 일단 채팅 받아오기
        System.out.println(message.getMessage());

        sendingOperations.convertAndSend("/topic/auction/room/" + message.getRoomId(), message);

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

        runWithTimeout(() -> {
            // 여기에 실행하고자 하는 코드를 넣으세요
            // 예: HTTP 요청, 파일 읽기 등
            System.out.println("실행중");
            return "";
        }, 20, TimeUnit.SECONDS); // 타임아웃을 5초로 설정
    }
}
