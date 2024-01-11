package com.hoarse.auction.web.dto.member.request;

import com.hoarse.auction.web.entity.role.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberRequest {

    private String name;

    private String phone;

    private String password;

    private String username;

    private Role role;
}
