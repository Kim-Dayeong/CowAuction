package com.hoarse.auction.web.controller.auction;

import com.hoarse.auction.web.entity.chat.ChatRoom;
import com.hoarse.auction.web.service.auction.AuctionRoomService;
import com.hoarse.auction.web.service.chat.ChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/auction")
public class AuctionController {

    private final ChatRoomService chatRoomService;
    private final AuctionRoomService auctionRoomService;

    // 경매 채팅방 생성
    @PostMapping
    public ChatRoom createRoom(@RequestParam String name){
        return chatRoomService.createRoom(name);
    }


    // 채팅방 입장
    @GetMapping
    public List<ChatRoom> findAllRooms(){
        return chatRoomService.findAllRoom();
    }


//
//    @MessageMapping("/bid/{itemId}")
//    public void placeBid(@DestinationVariable String itemId, Bid bid) {
//        auctionService.placeBid(itemId, bid);
//    }


}
