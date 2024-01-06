package com.hoarse.auction.web.service.member;

import com.hoarse.auction.web.config.jwt.JwtTokenProvider;
import com.hoarse.auction.web.dto.member.request.MemberSignUpRequest;

import com.hoarse.auction.web.entity.member.Member;
import com.hoarse.auction.web.entity.role.Role;
import com.hoarse.auction.web.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

    // 회원가입
    @Transactional

    public Member signUp(MemberSignUpRequest requestDto) throws Exception {
        if (memberRepository.findByusername(requestDto.getUsername()).isPresent()) {
            throw new Exception("이미 존재하는 아이디 입니다.");
        }
        if (!requestDto.getPassword().equals(requestDto.getCheckedPassword())) {
            throw new Exception("비밀번호가 일치하지 않습니다.");
        }

        Member member = createSignupFormOfUser(requestDto);
        memberRepository.save(member);
        return member;
    }


    private Member createSignupFormOfUser(final MemberSignUpRequest req){
        return Member.builder()
                .username(req.getUsername())
                .password(passwordEncoder.encode(req.getPassword()))
                .name(req.getName())
                .phone(req.getPhone())
                .role(Role.USER)
                .build();
    }

    // 로그인
    @Transactional
    public String login(Map<String, String> members) {

        Member member = memberRepository.findByusername(members.get("username"))
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 아이디 입니다."));

        String password = members.get("password");
        if (!member.checkPassword(passwordEncoder, password)) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }

        List<String> roles = new ArrayList<>();
        roles.add(member.getRole().name());

        return jwtTokenProvider.createToken(member.getUsername(), roles);


    }
}
