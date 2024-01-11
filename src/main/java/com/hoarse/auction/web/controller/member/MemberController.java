package com.hoarse.auction.web.controller.member;

import com.hoarse.auction.web.config.jwt.JwtConfig;

import com.hoarse.auction.web.dto.member.MemberDto;
import com.hoarse.auction.web.dto.member.request.MemberRequest;

import com.hoarse.auction.web.service.member.MemberService;
import io.jsonwebtoken.Jwts;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Slf4j
@RestController
@RequestMapping("/members")
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



}
