//package com.hoarse.auction.web.module.socket;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.messaging.simp.stomp.StompCommand;
//import org.springframework.messaging.simp.stomp.StompHeaders;
//import org.springframework.messaging.simp.stomp.StompSession;
//import org.springframework.messaging.simp.user.SimpUserRegistry;
//import org.springframework.messaging.support.ChannelInterceptor;
//
//@Configuration
//public class StompHandler implements ChannelInterceptor {
//
//    @Autowired
//    private SimpUserRegistry userRegistry;
//
//    @Override
//    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
//        System.out.println("New session established: " + session.getSessionId());
//    }
//
//    @Override
//    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
//        System.out.println("Handle exception in session: " + session.getSessionId() + ", " + exception.getMessage());
//    }
//
//    @Override
//    public void handleTransportError(StompSession session, Throwable exception) {
//        System.out.println("Transport error in session: " + session.getSessionId() + ", " + exception.getMessage());
//    }
//
//    @Override
//    public Type getPayloadType(StompHeaders headers) {
//        return byte[].class;
//    }
//
//    @Override
//    public void handleFrame(StompHeaders headers, Object payload) {
//        System.out.println("Received message: " + new String((byte[]) payload));
//    }
//}
