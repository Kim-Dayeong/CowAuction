package com.hoarse.auction.web.dto.chat;

import com.hoarse.auction.web.entity.member.Member;
import lombok.*;

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
    private Member sender;
    private String message;
    private String time;

}
