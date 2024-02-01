package com.hoarse.auction.web.service.auction;

import com.hoarse.auction.web.entity.auction.AuctionMessage;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.concurrent.TimeUnit;

import static org.apache.commons.lang3.StringUtils.isNumeric;

@Service
public class AuctionService {

    private static final String AUCTION_KEY = "auction";
    private static final long AUCTION_DURATION = TimeUnit.MINUTES.toMillis(1); // 1분

    // 기본값 설정
    public void auctionInit(){
        Jedis jedis = new Jedis("localhost", 6379);
        String key = "backupKey";
        String value = "3000000";
        jedis.set(key,value);
    }

    // 값 비교 후 더 높은값 제시
    public void auctionCompare(AuctionMessage message) {
        try (Jedis jedis = new Jedis("localhost", 6379)) {
            if(jedis.get("backupKey") == null){
                auctionInit();
            }
            String backupKeyString = jedis.get("backupKey");

            if (backupKeyString != null) {
                try {
                    Long backupKey = Long.valueOf(backupKeyString);

                    // value 자료형 구분 후 long으로 변환
                    if (isNumeric(message.getMessage())) {
                        Long value = Long.parseLong(message.getMessage());

                        // backupkey와 key의 값 비교
                        if (value > backupKey) {
                            auction(String.valueOf(value));
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

    private boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
    private void auction(String value) {

        long auctionStartTime = System.currentTimeMillis();
        long auctionEndTime = auctionStartTime + AUCTION_DURATION;

       Jedis jedis = new Jedis("localhost", 6379);
            // 경매 시작 시간 저장
            jedis.set("auctionStartTime", String.valueOf(auctionStartTime));

            // 경매 진행 중...
            // Redis에 값 저장 (경매 종료 시간 이전에만 저장, 값은 비교를 위해 두번 저장)
            long currentTime = System.currentTimeMillis();
            if (currentTime < auctionEndTime) {
                String key = AUCTION_KEY + ":" + auctionStartTime ;
                jedis.set(key, value);
                String backupKey = AUCTION_KEY + ":" + auctionStartTime + "backup";
                String backupValue = value;
                jedis.set(backupKey,backupValue);

                System.out.println("Value saved in Redis: " + value);
                System.out.println(key);
            } else {
                System.out.println("Auction has ended. Value will not be saved in Redis.");
            }
        }
    }




