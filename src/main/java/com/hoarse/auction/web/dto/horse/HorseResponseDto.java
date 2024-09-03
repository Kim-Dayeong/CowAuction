package com.hoarse.auction.web.dto.horse;

import com.hoarse.auction.web.entity.horse.Horse;
import com.hoarse.auction.web.entity.member.Member;
import lombok.Getter;

@Getter
public class HorseResponseDto {

    private final Long id;
    private final String name;
    private final String birth;
    private final String furcolor;
    private final Member owner;
    private final Horse mother;
    private final Horse father;
    private final Member producer;
    private final String uniqueNum;


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
