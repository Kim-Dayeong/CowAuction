package com.hoarse.auction.web.service.auction;

import com.hoarse.auction.web.entity.auction.AuctionRoom;
import com.hoarse.auction.web.entity.hoarse.Hoarse;
import com.hoarse.auction.web.entity.member.Member;
import com.hoarse.auction.web.repository.Auction.AuctionRoomRepository;
import com.hoarse.auction.web.repository.hoarse.HoarseRepository;
import com.hoarse.auction.web.repository.member.MemberRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.security.Principal;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AuctionRoomService {

    private final HoarseRepository hoarseRepository;
    private final AuctionRoomRepository auctionRoomRepository;
    private final MemberRepository memberRepository;

    private Map<String, AuctionRoom> auctionRooms;


    @PostConstruct
    //의존관계 주입완료되면 실행되는 코드
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

    // 유저 정보 불러오기
    public Member findMember(Long userid){
        Member member = memberRepository.findById(userid)
                .orElseThrow(() -> new BadCredentialsException("사용자 정보를 찾을 수 없습니다."));
        return member;

    }

    //채팅방 하나 불러오기
    public AuctionRoom findById(String roomId) {
        return auctionRooms.get(roomId);
    }

    //채팅방 생성
    public AuctionRoom createRoom(String name, String uniqueNum) {
        Hoarse hoarse = hoarseRepository.findByuniqueNum(uniqueNum);
        AuctionRoom auctionRoom =AuctionRoom.create(name, hoarse);

//        auctionRoomRepository.save(auctionRoom);
        auctionRooms.put(auctionRoom.getRoomId(), auctionRoom);
        return auctionRoom;

    }



}
