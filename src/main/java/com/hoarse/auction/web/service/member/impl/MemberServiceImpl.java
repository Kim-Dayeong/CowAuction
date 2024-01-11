package com.hoarse.auction.web.service.member.impl;

import com.hoarse.auction.web.dto.member.MemberDto;
import com.hoarse.auction.web.dto.member.request.MemberRequest;
import com.hoarse.auction.web.entity.member.Member;
import com.hoarse.auction.web.repository.member.MemberRepository;
import com.hoarse.auction.web.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;



    @Override
    public MemberDto createUser(MemberRequest memberRequest) {
        Member member = memberRepository.save(
                Member.builder().password(bCryptPasswordEncoder.encode(memberRequest.getPassword())).username(memberRequest.getUsername()).role(memberRequest.getRole()).build());
        return MemberDto.builder().id(member.getId()).password(member.getPassword()).role(member.getRole()).username(member.getUsername()).build();
    }

    @Override
    public MemberDto findUser(String email) {
        Member member = Optional.ofNullable(memberRepository.findByUsername(email)).orElseThrow(()->new BadCredentialsException("회원 정보를 찾을 수 없습니다."));
        return MemberDto.builder().id(member.getId()).password(member.getPassword()).role(member.getRole()).username(member.getUsername()).build();
    }

    @Override
    public MemberDto findByEmailAndPassword(String email, String password) {
        Member member = Optional.ofNullable(memberRepository.findByUsername(email)).orElseThrow(()->new BadCredentialsException("이메일이나 비밀번호를 확인해주세요."));

        if (bCryptPasswordEncoder.matches(password,member.getPassword()) == false) {
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        }

        return MemberDto.builder().id(member.getId()).password(member.getPassword()).role(member.getRole()).username(member.getUsername()).build();
    }

    @Override
    public List<MemberDto> findAll() {
        return memberRepository.findAll().stream().map(u->MemberDto.builder().id(u.getId()).password(u.getPassword()).role(u.getRole()).username(u.getUsername()).build()).collect(Collectors.toList());
    }






}
