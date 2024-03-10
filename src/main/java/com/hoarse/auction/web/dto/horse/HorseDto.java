package com.hoarse.auction.web.dto.horse;

import com.hoarse.auction.web.entity.horse.Horse;
import com.hoarse.auction.web.entity.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HorseDto {

    private Long id;
    private String name;
    private String birth;
    private String furcolor;
    private Member owner;
    private Horse mother;
    private Horse father;
    private Member producer;
    private String uniqueNum;

    @Builder
    private HorseDto(Long id, String name, String birth, String furcolor, Member owner,
                     Horse mother, Horse father, Member producer, String uniqueNum){
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
