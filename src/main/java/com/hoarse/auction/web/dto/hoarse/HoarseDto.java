package com.hoarse.auction.web.dto.hoarse;

import com.hoarse.auction.web.entity.hoarse.Hoarse;
import com.hoarse.auction.web.entity.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HoarseDto {

    private Long id;
    private String name;
    private String birth;
    private String furcolor;
    private Member owner;
    private Hoarse mother;
    private Hoarse father;
    private Member producer;
    private String uniqueNum;

    @Builder
    private HoarseDto(Long id, String name, String birth, String furcolor, Member owner,
                      Hoarse mother, Hoarse father, Member producer, String uniqueNum){
        this.id = id;
        this.name = name;
        this.birth = birth;
        this.furcolor = furcolor;
        this.owner = owner;
        this.mother = mother;
        this.father = father;
        this.producer = producer;
        this.uniqueNum = uniqueNum;
    }

}
