package com.hoarse.auction.web.service.auth;

import com.hoarse.auction.web.config.jwt.JwtConfig;
import com.hoarse.auction.web.entity.member.Member;
import com.hoarse.auction.web.repository.member.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final MemberRepository memberRepository;
    private final JwtConfig jwtConfig;


    //Access token 발급 메서드



    // refresh 토큰 발급 메서드


    // 토큰에서 member 추출
    public Member getMemberFromToken(String token){

       Long memberId = Long.valueOf(jwtConfig.getAuthentication(token).getName()); //getName은 userid 받아옴

        return memberRepository.findById(memberId).orElseThrow(() -> new EntityNotFoundException("유저 정보를 찾을 수 없습니다"));
    }
}
