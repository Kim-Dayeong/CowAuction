package com.hoarse.auction.web.dto.auction;

import com.hoarse.auction.web.dto.chat.ChatMessageDto;
import com.hoarse.auction.web.entity.member.Member;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuctionMessageDto {

    public enum MessageType{
        ENTER, TALK , QUIT
    }

    private ChatMessageDto.MessageType type;
    private String roomId;
    private Member sender;
    private String message;
    private String time;
    private String token;
    private String username;
}
