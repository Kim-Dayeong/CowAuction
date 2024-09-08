package com.hoarse.auction.web.dto.member;

import com.hoarse.auction.web.entity.role.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberRequestDto {

    @NotBlank(message = "이름은 필수 입력 항목입니다.")
    private String name;

    @NotBlank(message = "전화번호는 필수 입력 항목입니다.")
    private String phone;

    @NotBlank(message = "비밀번호는 입력 항목입니다.")
    private String password;

    @NotBlank(message = "아이디는 필수 입력 항목입니다.")
    private String username;

    private Role role;

//    private Role role;
}
