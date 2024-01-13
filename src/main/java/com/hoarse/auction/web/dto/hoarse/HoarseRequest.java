package com.hoarse.auction.web.dto.hoarse;

import com.hoarse.auction.web.entity.hoarse.Hoarse;
import com.hoarse.auction.web.entity.member.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HoarseRequest {
    private String name;
    private String birth;
    private String furcolor;
    private Member owner;
    private Hoarse mother;
    private Hoarse father;
    private Member producer;

}
