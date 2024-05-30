package com.hoarse.auction.web.controller.chat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoarse.auction.web.dto.chat.ChatMessageDto;
import com.hoarse.auction.web.entity.chat.ChatMessage;
import com.hoarse.auction.web.repository.chat.ChatMessageRepository;
import com.hoarse.auction.web.service.chat.ChatMessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Response;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

@RestController
@RequiredArgsConstructor
@Tag(name = "채팅 메시지 API")
public class ChatMessageController {

    private final SimpMessageSendingOperations sendingOperations;


    private final ChatMessageService chatMessageService;

    @Operation(summary = "채팅 내역 조회 API")
    @GetMapping("api/chat/message/{roomId}") // 대화 내역 조회
    public ResponseEntity<List<ChatMessageDto>> roomMessage(@PathVariable String roomId) {

        return ResponseEntity.ok(chatMessageService.loadChat(roomId));

    }

    @Operation(summary = "채팅 전송 API")
    @MessageMapping("/chat/message")
    public void enter(ChatMessage message) {
        if (ChatMessage.MessageType.ENTER.equals(message.getType())) {
            message.setMessage(message.getSender() + "님이 입장하였습니다.");
        }

        // 채팅 저장
        // 일단 채팅 받아오기
        System.out.println(message.getMessage());

        sendingOperations.convertAndSend("/topic/chat/room/" + message.getRoomId(), message);

        chatMessageService.saveChat(message);

    }

}


