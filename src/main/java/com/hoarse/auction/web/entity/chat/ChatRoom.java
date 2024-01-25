package com.hoarse.auction.web.entity.chat;

import com.hoarse.auction.web.dto.chat.ChatMessageDto;
import com.hoarse.auction.web.service.chat.ChatService;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.socket.WebSocketSession;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Data
@Getter
@Setter
@NoArgsConstructor
public class ChatRoom implements Serializable {

        private static final long serialVersionUID = 6494678977089006639L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String roomId;

    private String roomName;

//    private Set<WebSocketSession> sessions = new HashSet<>();

    public static ChatRoom create(String name) {
        ChatRoom room = new ChatRoom();
        room.roomId = UUID.randomUUID().toString();
        room.roomName = name;
        return room;
    }




}
