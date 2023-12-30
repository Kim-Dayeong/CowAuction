package com.hoarse.auction.web.entity.hoarse;

import com.hoarse.auction.web.entity.member.Member;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "Hoarse")
public class Hoarse {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="HoarseId")
    private Long id;

    @Column
    private String name;

    @Column
    private String birth;

    @Column
    private String furcolor;

    @Column
    private Integer nearvalue; // 근친율


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "father_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Hoarse father;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mother_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Hoarse mother;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member producer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member owner;

}
