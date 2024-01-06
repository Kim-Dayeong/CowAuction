package com.hoarse.auction.web.entity.role;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    ADMIN("ROLE_ADMIN","관리자"),
    USER("ROLE_USER","사용자"),
    OWNER("ROLE_OWNER","경매주");


    private final String key;
    private final String title;
}
