package com.hoarse.auction.web.dto.member;

import com.hoarse.auction.web.entity.role.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberDto {

    private Long id;

    private String name;

    private String phone;

    private String password;

    private String username;

    private Role role;


    @Builder
    private MemberDto(Long id, String password, Role role, String username, String name, String phone) {
        this.id = id;
        this.password = password;
        this.role = role;
        this.username = username;
        this.name = name;
        this.phone = phone;
    }
}
