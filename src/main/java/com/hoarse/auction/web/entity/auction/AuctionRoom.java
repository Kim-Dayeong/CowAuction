package com.hoarse.auction.web.entity.auction;

import com.hoarse.auction.web.entity.horse.Horse;
import com.hoarse.auction.web.entity.member.Member;
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
    private String roomId;

    private String roomName;

    private Member member;

    @OneToOne
    @JoinColumn(name = "hoarse_id") //단방향 일대일 매핑
    Horse horse;

    private String sender;



    public static AuctionRoom create(String name, Horse horse, Member member) {
        AuctionRoom room = new AuctionRoom();
        room.roomId = UUID.randomUUID().toString();
        room.roomName = name;
        room.horse = horse;
        room.member = member;
        return room;
    }
}

