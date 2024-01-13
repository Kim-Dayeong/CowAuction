package com.hoarse.auction.web.controller.member;

import com.hoarse.auction.web.config.jwt.JwtConfig;

import com.hoarse.auction.web.dto.member.MemberDto;
import com.hoarse.auction.web.dto.member.MemberRequest;

import com.hoarse.auction.web.service.member.MemberService;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final JwtConfig jwtConfig;


    @PostMapping("/signup")
    public MemberDto createUser(MemberRequest memberRequest) {
        return memberService.createUser(memberRequest);
    }

    @PostMapping("/login")
    public String login(MemberRequest memberRequest) {
        MemberDto member = memberService.findByEmailAndPassword(memberRequest.getUsername(), memberRequest.getPassword());
        return jwtConfig.createToken(member.getUsername(), Arrays.asList(member.getRole().getValue()));
    }

    @GetMapping("/admin")
    @PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
    public List<MemberDto> findAllUser() {
        return memberService.findAll();
    }

    @GetMapping("/my")
    public MemberDto findUser(Authentication authentication) {
        if (authentication == null) {
            throw new BadCredentialsException("회원 정보를 찾을 수 없습니다.(로그인 안됨)");
        }
        return memberService.findUser(authentication.getName());
    }




}
