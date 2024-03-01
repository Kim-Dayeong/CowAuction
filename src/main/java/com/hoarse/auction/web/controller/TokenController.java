package com.hoarse.auction.web.controller;

import com.hoarse.auction.web.config.jwt.JwtConfig;
import com.hoarse.auction.web.entity.member.Member;
import com.hoarse.auction.web.repository.member.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class TokenController {

    private final JwtConfig jwtConfig;

    private final MemberRepository memberRepository;

    @GetMapping("/info")
    public ResponseEntity<String> getMemberinfo(HttpServletRequest request) {

        String token = jwtConfig.resolveToken(request);

        if (token != null && jwtConfig.validateToken(token)) {
            // 토큰이 유효하면
            String username = jwtConfig.getAuthentication(token).getName();
        Member member = memberRepository.findById(Long.valueOf(username))
                .orElseThrow(() -> new IllegalArgumentException("해당하는 멤버를 찾을 수 없습니다."));


        return ResponseEntity.ok("{\"name\": \"" + member.getName() + "\"," +
                " \"username\": \"" + member.getUsername() + "\"}");

        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("토큰이 유효하지 않습니다");

    }
//
//    @GetMapping("/token/request")
//    public ResponseEntity<String> getMember(HttpServletRequest request,   Authentication authentication ) {
//      String name = authentication.getName();
//
//        return ResponseEntity.ok("{\"name\": \"" +  name+ "\"}");
//    }

}