package com.hoarse.auction.web.entity.auction;

import com.hoarse.auction.web.entity.horse.Horse;
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

    private static final long serialVersionUID = 6494678977089006639L; // 수정

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String roomId;

    private String roomName;

    @OneToOne
    @JoinColumn(name = "hoarse_id") //단방향 일대일 매핑
    Horse hoarse;

    private String sender;


//    private Set<WebSocketSession> sessions = new HashSet<>();

    public static AuctionRoom create(String name, Horse hoarse) {
        AuctionRoom room = new AuctionRoom();
        room.roomId = UUID.randomUUID().toString();
        room.roomName = name;
        room.hoarse = hoarse;
        return room;
    }
}

