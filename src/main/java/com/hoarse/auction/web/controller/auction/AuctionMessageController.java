package com.hoarse.auction.web.controller.auction;

import com.hoarse.auction.web.entity.auction.AuctionMessage;
import com.hoarse.auction.web.entity.chat.ChatMessage;
import com.hoarse.auction.web.service.auction.AuctionService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class AuctionMessageController {
    private final SimpMessageSendingOperations sendingOperations;
    private final AuctionService auctionService;


    @MessageMapping("/auction/message")
    public void enter(AuctionMessage message) {
        if (ChatMessage.MessageType.ENTER.equals(message.getType())) {
            message.setMessage(message.getSender() + "님이 입장하였습니다.");

        }
        // 채팅 저장
        // 일단 채팅 받아오기
        System.out.println(message.getMessage());

        sendingOperations.convertAndSend("/topic/auction/room/" + message.getRoomId(), message);
        // 값 비교
        auctionService.auctionCompare(message);


    }

}
