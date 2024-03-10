package com.hoarse.auction.web.dto.horse;

import com.hoarse.auction.web.entity.horse.Horse;
import com.hoarse.auction.web.entity.member.Member;
import lombok.Getter;

@Getter
public class HorseResponseDto {

    private Long id;
    private String name;
    private String birth;
    private String furcolor;
    private Member owner;
    private Horse mother;
    private Horse father;
    private Member producer;
    private String uniqueNum;


    public HorseResponseDto(Horse entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.birth = entity.getBirth();
        this.furcolor = entity.getFurcolor();
        this.owner = entity.getOwner();
        this.mother = entity.getMother();
        this.father = entity.getFather();
        this.producer = entity.getProducer();
        this.uniqueNum = entity.getUniqueNum();
    }
}
