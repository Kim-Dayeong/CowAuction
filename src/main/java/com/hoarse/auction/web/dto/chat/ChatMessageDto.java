package com.hoarse.auction.web.dto.chat;

import com.hoarse.auction.web.entity.chat.ChatMessage;
import com.hoarse.auction.web.entity.member.Member;
import lombok.*;

import java.sql.Timestamp;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageDto {

    public enum MessageType{
    ENTER, TALK , QUIT
    }
    private Long id;
    private MessageType type;
    private String roomId;
    private String sender;
    private String message;
    private Timestamp time;

    public ChatMessageDto(ChatMessage chatMessage) {
        this.id = chatMessage.getId();
        this.roomId = chatMessage.getRoomId();
        this.sender = chatMessage.getSender();
        this.message = chatMessage.getMessage();
        this.time = chatMessage.getTime();
    }


}


