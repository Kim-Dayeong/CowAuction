package com.hoarse.auction.web.dto.member.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MemberLoginRequest {
    private String employNumber;
    private String password;
}
