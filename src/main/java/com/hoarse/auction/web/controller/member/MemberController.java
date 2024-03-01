package com.hoarse.auction.web.controller.member;

import com.hoarse.auction.web.config.jwt.JwtConfig;

import com.hoarse.auction.web.dto.member.MemberDto;
import com.hoarse.auction.web.dto.member.MemberRequest;

import com.hoarse.auction.web.service.member.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;


import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final JwtConfig jwtConfig;

    @GetMapping("/info")
    public String getMemberInfo(HttpServletRequest request){

        String token = jwtConfig.resolveToken(request);

        if(token != null && jwtConfig.validateToken(token)){
            // 토큰이 유효하면
            String username = jwtConfig.getAuthentication(token).getName();
            return username;
        }

        return "토큰이 유효하지 않습니다";

    }



    @PostMapping("/signup")
    public MemberDto createUser(@RequestBody MemberRequest memberRequest) {
        return memberService.createUser(memberRequest);
    }

//    @GetMapping("/test")
//    public String test(){
//
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//       ;SecurityContextHolder.getContext().getAuthentication().getName();
////        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
////        String username = authentication.getName();
//
//        return username;
//    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody MemberRequest memberRequest, HttpServletRequest request) {
        // 사용자 정보 확인 및 토큰 생성
        MemberDto member = memberService.findByEmailAndPassword(memberRequest.getUsername(), memberRequest.getPassword());
        String token = jwtConfig.createToken(member.getUsername(), Arrays.asList(member.getRole().getValue()));

        // 토큰을 응답으로 반환
        return ResponseEntity.ok(token);
    }
//    public String login(@RequestBody MemberRequest memberRequest, HttpServletRequest request) {
//        MemberDto member = memberService.findByEmailAndPassword(memberRequest.getUsername(), memberRequest.getPassword());
//
//        return jwtConfig.createToken(member.getUsername(), Arrays.asList(member.getRole().getValue()));
//    }



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
