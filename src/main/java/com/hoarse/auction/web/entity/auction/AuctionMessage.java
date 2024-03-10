package com.hoarse.auction.web.entity.auction;

import com.hoarse.auction.web.entity.chat.ChatMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuctionMessage {
    public enum MessageType {
        ENTER, TALK
    }

    private ChatMessage.MessageType type;
    //채팅방 ID
    private String roomId;
    //보내는 사람
    private String sender;
    //내용
    private String message;

    private String username;

    private String jwt;
}
