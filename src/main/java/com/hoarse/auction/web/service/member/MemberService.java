package com.hoarse.auction.web.service.member;

import com.hoarse.auction.web.dto.member.MemberDto;
import com.hoarse.auction.web.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Value("${jwt.token.secret}")
    private String secretkey;
    private final long expireTimeMs = 1000 * 60 * 60 * 24 * 7; // 토큰 7일


    public MemberDto register(MemberRegisterRequest request) {
        memberRepository.findByEmployNumber(request.getEmployNumber())
                .ifPresent(member -> {
                    throw new RuntimeException();
                });
}
