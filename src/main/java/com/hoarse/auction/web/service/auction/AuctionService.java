package com.hoarse.auction.web.service.auction;

import com.hoarse.auction.web.entity.Bid;
import com.hoarse.auction.web.entity.auction.AuctionRoom;
import com.hoarse.auction.web.repository.Auction.AuctionRepository;
import com.hoarse.auction.web.repository.Auction.AuctionRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class AuctionService {

    private final SimpMessagingTemplate messagingTemplate;
    @Autowired
    private AuctionRoomRepository auctionRoomRepository;

    // 경매 방 생성
    public AuctionRoom createAuctoinRoom(String roomName){
        AuctionRoom auctionRoom = new AuctionRoom();
        auctionRoom.setRoomName(roomName);
        return auctionRoomRepository.save(auctionRoom);
    }

    @Autowired
    public AuctionService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void placeBid(String itemId, Bid bid) {
        // 경매 관련 로직 수행

        // Redis에 경매 상태 저장
        // redisTemplate.opsForValue().set("auction:" + itemId, auctionState);

        // WebSocket으로 입찰 정보 브로드캐스팅
        messagingTemplate.convertAndSend("/topic/auction/" + itemId, bid);
    }
}
