package com.hoarse.auction.web.entity;

import com.hoarse.auction.web.entity.horse.Horse;
import com.hoarse.auction.web.entity.member.Member;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
@Entity(name = "Bid")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Bid {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Bid_Id")
    private Long id;


    @OneToOne
    private Horse hoarse;

    @OneToOne
    private Member bidder; // 입찰자
    private BigDecimal amount; // 낙찰가



}
