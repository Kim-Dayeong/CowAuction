package com.hoarse.auction.web.service.auction;

import com.hoarse.auction.web.entity.auction.AuctionMessage;
import com.hoarse.auction.web.entity.auction.AuctionRoom;
import com.hoarse.auction.web.entity.hoarse.Hoarse;
import com.hoarse.auction.web.repository.Auction.AuctionRoomRepository;
import com.hoarse.auction.web.repository.hoarse.HoarseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.lang3.StringUtils.isNumeric;
import static org.apache.commons.lang3.StringUtils.stripStart;

@Service
@AllArgsConstructor
public class AuctionService {

    private static final String AUCTION_KEY = "auction";

    private final HoarseRepository hoarseRepository;
    private final AuctionRoomRepository auctionRoomRepository;
//    private static final long AUCTION_DURATION = TimeUnit.MINUTES.toMillis(1); // 1분

    // 기본값 설정
    public void auctionInit(){
        Jedis jedis = new Jedis("localhost", 6379);
        String key = "backupKey";
        String value = "300"; // 단위: 만원
        jedis.set(key,value);
    }

    // 값 비교 후 더 높은값 제시

    public void auctionCompare(AuctionMessage message) {
        try (Jedis jedis = new Jedis("localhost", 6379)) {
            auctionInit();

            String backupKeyString = jedis.get("backupKey");

            if (backupKeyString != null) {
                try {
                    long backupKey = Long.parseLong(backupKeyString);

                    // value 자료형 구분 후 long으로 변환
                    if (isNumeric(message.getMessage())) {
                        long value;
                        try {
                            value = Long.parseLong(message.getMessage());
                        } catch (NumberFormatException e) {
                            System.out.println("유효한 숫자가 아닙니다.");
                            return; // 숫자가 아닌 경우 바로 메서드 종료
                        }

                        // backupkey와 key의 값 비교
                        if (value > backupKey) {
                            auction(String.valueOf(value), message);
                        } else {
                            System.out.println("이전값 보다 더 높은값을 제시해야 합니다.");
                        }
                    } else {
                        System.out.println("유효한 숫자가 아닙니다.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("backupKey의 값이 유효한 숫자가 아닙니다.");
                }
            } else {
                System.out.println("backupKey의 값이 null입니다.");
            }
        }
    }

    public void saveChatMessage(String roomId, String message){
        try (Jedis jedis = new Jedis("localhost", 6379)){
            jedis.rpush(roomId, message);
        }
    }

//    public void auctionCompare(AuctionMessage message) {
//        try (Jedis jedis = new Jedis("localhost", 6379)) {
//            auctionInit();
//
//            String backupKeyString = jedis.get("backupKey");
//
//            if (backupKeyString != null) {
//                try {
//                    long backupKey = Long.parseLong(backupKeyString);
//
//                    // value 자료형 구분 후 long으로 변환
//                    if (isNumeric(message.getMessage())) {
//                        long value;
//                        try {
//                            value = Long.parseLong(message.getMessage());
//                        } catch (NumberFormatException e) {
//                            System.out.println("유효한 숫자가 아닙니다.");
//                            return; // 숫자가 아닌 경우 바로 메서드 종료
//                        }
//
//                        // backupkey와 key의 값 비교
//                        if (value > backupKey) {
//                            auction(String.valueOf(value), message);
//                        } else {
//                            System.out.println("이전값 보다 더 높은값을 제시해야 합니다.");
//                        }
//                    } else {
//                        System.out.println("유효한 숫자가 아닙니다.");
//                    }
//                } catch (NumberFormatException e) {
//                    System.out.println("backupKey의 값이 유효한 숫자가 아닙니다.");
//                }
//            } else {
//                System.out.println("backupKey의 값이 null입니다.");
//            }
//        }
//    }

//    // 값 비교 후 더 높은값 제시
//    public void auctionCompare(AuctionMessage message) {
//        try (Jedis jedis = new Jedis("localhost", 6379)) {
//            auctionInit();
//
//            String backupKeyString = jedis.get("backupKey");
//
//            if (backupKeyString != null) {
//                try {
//                    long backupKey = Long.parseLong(backupKeyString);
//
//                    // value 자료형 구분 후 long으로 변환
//                    if (isNumeric(message.getMessage())) {
//                        long value = Long.parseLong(message.getMessage());
//
//                        // backupkey와 key의 값 비교
//                        if (value > backupKey) {
//                            auction(String.valueOf(value), message);
//                        } else {
//                            System.out.println("이전값 보다 더 높은값을 제시해야 합니다.");
//                        }
//                    } else {
//                        System.out.println("유효한 숫자가 아닙니다.");
//                    }
//                } catch (NumberFormatException e) {
//                    System.out.println("backupKey의 값이 유효한 숫자가 아닙니다.");
//                }
//            } else {
//                System.out.println("backupKey의 값이 null입니다.");
//            }
//        }
//    }
//

    private void auction(String value, AuctionMessage message) {

        AuctionRoom auctionRoom = auctionRoomRepository.findByRoomId(message.getRoomId());
        if (auctionRoom == null) {
            System.out.println("경매 방을 찾을 수 없습니다.");
            return;
        }
        Hoarse hoarse = auctionRoom.getHoarse();

        try (Jedis jedis = new Jedis("localhost", 6379)) {
            System.out.println("실행 분기 테스트");

            String key = AUCTION_KEY + ":" + hoarse.getName();
            jedis.set(key, value);
            String backupKey = AUCTION_KEY + ":" + hoarse.getName() + "backup";
            String backupValue = value;
            jedis.set(backupKey, backupValue);

            System.out.println("Value saved in Redis: " + value);
            System.out.println(key);
        } catch (NumberFormatException e) {
            System.out.println("Redis 연결 중 오류가 발생했습니다.");
        }
    }


//    private void auction(String value, AuctionMessage message) {
//        Optional<AuctionRoom> optionalAuctionRoom = auctionRoomRepository.findById(Long.valueOf(message.getRoomId()));
//
//        AuctionRoom auctionRoom = optionalAuctionRoom.orElse(null);
//
//        Hoarse hoarse = auctionRoom.getHoarse();
//
//
//       Jedis jedis = new Jedis("localhost", 6379);
//            // 경매 시작 시간 저장
//          //  jedis.set("auctionStartTime", String.valueOf(auctionStartTime));
//
//            // 경매 진행 중...
//            // Redis에 값 저장 (경매 종료 시간 이전에만 저장, 값은 비교를 위해 두번 저장)
//           // long currentTime = System.currentTimeMillis();
//            System.out.println("실행 분기 테스트");
//            Long endTime = Long.parseLong(jedis.get("endTime"));
//            if (System.currentTimeMillis() < endTime) {
//                String key = AUCTION_KEY + ":" + hoarse.getName() ;
//                jedis.set(key, value);
//                String backupKey = AUCTION_KEY + ":" + hoarse.getName() + "backup";
//                String backupValue = value;
//                jedis.set(backupKey,backupValue);
//
//                System.out.println("Value saved in Redis: " + value);
//                System.out.println(key);
//            } else {
//                System.out.println("옥션이 종료되었습니다 이후 값은 레디스 저장 안됨 ");
//                System.out.println("==========낙찰되었습니다 낙찰가 : "+ jedis.get(AUCTION_KEY + ":" +hoarse.getName()));
//                System.out.println("낙찰자:");
//
//            }
//        }
    }




