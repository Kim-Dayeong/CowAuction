package com.hoarse.auction.web.config.socket;

import com.hoarse.auction.web.module.socket.SocketHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

//@Configuration
//@EnableWebSocket
//public class WebSocketConfig implements WebSocketConfigurer {
//
//    @Autowired
//    SocketHandler socketHandler;
//
//    // 요청은 핸들러로 라우트 되고
//    // beforeHandshake메소드에서 헤더 중 필요한 값을 가져와 true값 반환하면 Upgrade 헤더와 함께 101 Switching Protocols 상태 코드를 포함한 응답 반환
//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        registry.addHandler(socketHandler, "/ws/chat","/ws/auction").setAllowedOrigins("*");
//
//
//    }
@RequiredArgsConstructor
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {



//     @Override
//     public void configureClientInboundChannel(ChannelRegistration registration) {
//     	registration.interceptors(stompHandler);
//     }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").withSockJS();
    }

}





