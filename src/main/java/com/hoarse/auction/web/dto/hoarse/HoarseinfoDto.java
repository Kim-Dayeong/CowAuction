package com.hoarse.auction.web.dto.hoarse;

import com.hoarse.auction.web.entity.hoarse.Hoarse;
import lombok.Getter;

@Getter
public class HoarseinfoDto {
    private String uuid;
    private Hoarse mother;
    private Hoarse father;
    private String producer;
}
