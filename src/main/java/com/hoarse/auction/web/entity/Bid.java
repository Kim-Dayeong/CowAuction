package com.hoarse.auction.web.entity;

import com.hoarse.auction.web.entity.hoarse.Hoarse;
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

    @Column
    @OneToOne
    private Hoarse hoarse;

    @OneToOne
    private Member bidder; // 입찰자
    private BigDecimal amount; // 낙찰가



}
