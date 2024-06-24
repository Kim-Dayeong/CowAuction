package com.hoarse.auction.web.dto.horse;

import com.hoarse.auction.web.entity.horse.Horse;
import com.hoarse.auction.web.entity.member.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HorseupdateDto {
    private String name;
    private String birth;
    private String furcolor;
    private Horse mother;
    private Horse father;
    private Member owner;
    private Member producer;
    private String uniqueNum;
    private Long bidPrice;
}