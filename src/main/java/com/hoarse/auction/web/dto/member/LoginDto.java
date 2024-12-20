package com.hoarse.auction.web.dto.member;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {

    @NotBlank(message = "아이디는 필수 입력 항목입니다.")
    private String username;

    @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
    private String password;
}
