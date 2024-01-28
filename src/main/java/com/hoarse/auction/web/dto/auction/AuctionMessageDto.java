package com.hoarse.auction.web.dto.auction;

import com.hoarse.auction.web.dto.chat.ChatMessageDto;
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
    private String sender;
    private String message;
    private String time;
}
