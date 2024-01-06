package com.hoarse.auction.web.dto.member.request;

import com.hoarse.auction.web.entity.member.Member;
import com.hoarse.auction.web.entity.role.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MemberSignUpRequest {

    @NotBlank(message = "아이디를 입력해주세요")
    @Pattern(regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$"
    ,message = "이메일 형식으로 입력해주세요.")
    private String username;

    @NotBlank(message = "이름을 입력해주세요")
    @Pattern(regexp = "^[가-힣A-Za-z]*$", message = "본명만 입력할수 있습니다.")
    private String name;

    @NotBlank(message = "전화번호를 입력해주세요")
    @Pattern(regexp = "^[0-9]*$", message = "숫자가 아닌 값이 포함되어 있습니다.")
    private String phone;

    @NotBlank(message = "비밀번호를 입력해주세요")
    @Pattern(regexp = "^[A-Za-z0-9]{8,30}$", message = "비밀번호는 8~30 자리이면서 1개 이상의 알파벳과 숫자를 포함해야합니다.")
    private String password;

    private String checkedPassword;

    private Role role;

    @Builder
    public Member toEntity(){
        return Member.builder()
                .username(username)
                .name(name)
                .password(password)
                .role(Role.USER)
                .build();
    }
}
