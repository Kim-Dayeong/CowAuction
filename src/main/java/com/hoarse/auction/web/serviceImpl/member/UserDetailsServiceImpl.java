package com.hoarse.auction.web.serviceImpl.member;

import com.hoarse.auction.web.entity.member.Member;
import com.hoarse.auction.web.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;



    @Override
    public Member loadUserByUsername(String username) {
        return Optional.ofNullable(memberRepository.findByUsername(username)).orElseThrow(() -> new BadCredentialsException("이메일을 확인해주세요."));
    }
}
