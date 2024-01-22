//package com.hoarse.auction.web.entity.auction;
//
//import com.hoarse.auction.web.entity.hoarse.Hoarse;
//import com.hoarse.auction.web.entity.member.Member;
//import jakarta.persistence.*;
//import lombok.*;
//
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
//@Builder
//@Entity(name = "Item")
//public class Item {
//
//    @jakarta.persistence.Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name="Item_Id")
//    private Long id;
//
//    @Column
//    @OneToOne
//    private Hoarse hoarse;
//
//    @OneToOne
//    private Member owner; // 낙찰자
//
//    // 낙찰 시간
//
//    int startPrice; // 기본값 450(만원)
//    int finalPrice; // 최종 낙찰가
//
//
//
//
//
//}
