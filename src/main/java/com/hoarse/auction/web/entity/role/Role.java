package com.hoarse.auction.web.entity.role;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public enum Role {

//    ADMIN("ROLE_ADMIN","관리자"),
//    USER("ROLE_USER","사용자"),
//    OWNER("ROLE_OWNER","경매주");
//
//
//    private final String key;
//    private final String title;

    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private String value;
}
