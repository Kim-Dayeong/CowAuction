package com.hoarse.auction.web.entity.chat;

import com.hoarse.auction.web.entity.hoarse.Hoarse;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;


@Data
@Getter
@Setter
@NoArgsConstructor
public class ChatRoom implements Serializable {

        private static final long serialVersionUID = 6494678977089006639L; // 수정

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
