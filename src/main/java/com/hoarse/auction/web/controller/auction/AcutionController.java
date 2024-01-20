package com.hoarse.auction.web.controller.auction;

import com.hoarse.auction.web.entity.chat.ChatRoom;
import com.hoarse.auction.web.service.chat.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/auction")
public class AcutionController {

    private final ChatService chatService;

    // 경매 채팅방 생성
    @PostMapping
    public ChatRoom createRoom(@RequestParam String name){
        return chatService.createRoom(name);
    }


    // 채팅방 입장
    @GetMapping
    public List<ChatRoom> findAllRooms(){
        return chatService.findAllRoom();
    }


    // 채팅방 퇴장



    // 채팅 내역 불러오기


    // 채팅 내역 삭제 (실시간 경매 종료)


}
