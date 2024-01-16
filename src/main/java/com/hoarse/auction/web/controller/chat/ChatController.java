package com.hoarse.auction.web.controller.chat;

import com.hoarse.auction.web.entity.chat.ChatRoom;
import com.hoarse.auction.web.serviceImpl.chat.ChatServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {

    private final ChatServiceImpl chatService;
    @PostMapping
    public ChatRoom createRoom(@RequestParam String name){
        return chatService.createRoom(name);
    }

    @GetMapping
    public List<ChatRoom> findAllRooms(){
        return chatService.findAllRoom();
    }

}
