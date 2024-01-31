package com.hoarse.auction.web.service.auction;

import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.concurrent.TimeUnit;

@Service
public class AuctionService {

    private static final String AUCTION_KEY = "auction";
    private static final long AUCTION_DURATION = TimeUnit.MINUTES.toMillis(10); // 10분 경매

    public static void main(String[] args) {
        long auctionStartTime = System.currentTimeMillis();
        long auctionEndTime = auctionStartTime + AUCTION_DURATION;

        try (Jedis jedis = new Jedis("localhost", 6379)) {
            // 경매 시작 시간 저장
            jedis.set("auctionStartTime", String.valueOf(auctionStartTime));

            // 경매 진행 중...
            // Redis에 값 저장 (경매 종료 시간 이전에만 저장)
            long currentTime = System.currentTimeMillis();
            if (currentTime < auctionEndTime) {
                String key = AUCTION_KEY + ":" + currentTime;
                String value = "Auction data...";
                jedis.set(key, value);
                System.out.println("Value saved in Redis: " + value);
            } else {
                System.out.println("Auction has ended. Value will not be saved in Redis.");
            }
        }
    }
}
