package com.hoarse.auction.web.service.auction;

import com.hoarse.auction.web.entity.auction.AuctionRoom;
import com.hoarse.auction.web.entity.chat.ChatRoom;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuctionRoomService {

    private Map<String, AuctionRoom> auctionRooms;

    @PostConstruct
    //의존관게 주입완료되면 실행되는 코드
    private void init() {
        auctionRooms = new LinkedHashMap<>();
    }

    //채팅방 불러오기
    public List<AuctionRoom> findAllRoom() {
        //채팅방 최근 생성 순으로 반환
        List<AuctionRoom> result = new ArrayList<>(auctionRooms.values());
        Collections.reverse(result);

        return result;
    }

    //채팅방 하나 불러오기
    public AuctionRoom findById(String roomId) {
        return auctionRooms.get(roomId);
    }

    //채팅방 생성
    public AuctionRoom createRoom(String name) {
        AuctionRoom auctionRoom =AuctionRoom.create(name);
        auctionRooms.put(auctionRoom.getRoomId(), auctionRoom);
        return auctionRoom;
    }


}
