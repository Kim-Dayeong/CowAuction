package com.hoarse.auction.web.entity.hoarse;

import com.hoarse.auction.web.entity.member.Member;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "Hoarse")
public class Hoarse {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="HoarseId")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String birth;

    @Column(nullable = false)
    private String furcolor;

    @Column
    private Integer nearvalue; // 근친율

    // 관심수 (경매 관심)


    @Column(unique = true, nullable = false)
    private String uniqueNum; // 고유 번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "father_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Hoarse father;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mother_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Hoarse mother;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member producer;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member owner;

    @OneToMany(mappedBy = "mother", fetch = FetchType.LAZY)
    private List<Hoarse> motherChildren = new ArrayList<>();

    @OneToMany(mappedBy = "father", fetch = FetchType.LAZY)
    private List<Hoarse> fatherChildren = new ArrayList<>();

    @Builder
    private Hoarse(String name, String birth, String furcolor,Hoarse mother, Hoarse father
    , Member owner, Member producer, String uniqueNum){
        this.name = name;
        this.birth = birth;
        this.furcolor = furcolor;
        this.mother = mother;
        this.father = father;
        this.owner = owner;
        this.producer = producer;
        this.uniqueNum = uniqueNum;

    }



}
