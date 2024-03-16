package com.hoarse.auction.web.entity.chat;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;



@Getter
@Setter
@NoArgsConstructor
@Entity
public class ChatRoom implements Serializable {

        private static final long serialVersionUID = 6494678977089006639L; // 수정

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;

    private String roomName;



//    private Set<WebSocketSession> sessions = new HashSet<>();

    public static ChatRoom create(String name) {
        ChatRoom room = new ChatRoom();
        room.roomName = name;
        return room;
    }




}
