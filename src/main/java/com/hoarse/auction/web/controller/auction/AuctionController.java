package com.hoarse.auction.web.controller.auction;

import com.hoarse.auction.web.entity.Bid;
import com.hoarse.auction.web.entity.chat.ChatRoom;
import com.hoarse.auction.web.service.auction.AuctionService;
import com.hoarse.auction.web.service.chat.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/auction")
public class AuctionController {

    private final ChatService chatService;
    private final AuctionService auctionService;

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


//
//    @MessageMapping("/bid/{itemId}")
//    public void placeBid(@DestinationVariable String itemId, Bid bid) {
//        auctionService.placeBid(itemId, bid);
//    }


}
