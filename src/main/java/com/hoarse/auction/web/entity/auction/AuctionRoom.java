package com.hoarse.auction.web.entity.auction;

import com.hoarse.auction.web.entity.chat.ChatRoom;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Data
@Getter
@Setter
@NoArgsConstructor
@Entity
public class AuctionRoom implements Serializable {

    private static final long serialVersionUID = 6494678977089006639L;

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String roomId;

    private String roomName;

//    private Set<WebSocketSession> sessions = new HashSet<>();

    public static AuctionRoom create(String name) {
        AuctionRoom room = new AuctionRoom();
        room.roomId = UUID.randomUUID().toString();
        room.roomName = name;
        return room;
    }
}

