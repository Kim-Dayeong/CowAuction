package com.hoarse.auction.web.controller.chat;

import com.hoarse.auction.web.entity.chat.ChatRoom;
import com.hoarse.auction.web.service.chat.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatRoomController {

    private final ChatService chatService;

    // 모든 채팅방 목록 반환
    @GetMapping("/rooms")
    public List<ChatRoom> room() {
        return chatService.findAllRoom();
    }
    // 채팅방 생성
//    @PostMapping("/room")
//    public ChatRoom createRoom(@RequestParam String name) {
//        return chatService.createRoom(name);
//    }


    @PostMapping("/room")
    public ChatRoom createRoom(@RequestParam String name) {
      return chatService.createRoom(name);
    }

    // 특정 채팅방 조회

    @GetMapping("/room/{roomId}")
    public ChatRoom roomInfo(@PathVariable String roomId) {
        return chatService.findById(roomId);
    }
}


