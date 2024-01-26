//package com.hoarse.auction.web.config.redis;
//
//
//import jakarta.annotation.PostConstruct;
//import jakarta.annotation.PreDestroy;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.data.redis.connection.RedisServer;
//
//@Configuration
//@Profile("local")
//@Slf4j
//public class EmbeddedRedisConfig {
//    @Value("${spring.redis.port}")
//    private int redisPort;
//
//    private RedisServer redisServer;
//
//    @PostConstruct
//    public void startRedisServer() {
//        redisServer = new RedisServer(redisPort);
//        redisServer.start();
//    }
//
//    @PreDestroy
//    public void stopRedis() {
//        if (redisServer != null) redisServer.stop();
//    }
//}