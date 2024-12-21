package com.hoarse.auction.web.service.redis;

import lombok.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TokenService {
    private final RedisTemplate<String, String> redisTemplate;

    public TokenService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveRefreshToken(String username, String refreshToken, long expiry) {
        String key = "refresh_token:" + username;
        redisTemplate.opsForValue().set(key, refreshToken, expiry, TimeUnit.MILLISECONDS);
    }

    public String getRefreshToken(String username) {
        String key = "refresh_token:" + username;
        return redisTemplate.opsForValue().get(key);
    }

    public void deleteRefreshToken(String username) {
        String key = "refresh_token:" + username;
        redisTemplate.delete(key);
    }
}
