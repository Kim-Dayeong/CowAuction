package com.hoarse.auction.web.service.member;

import com.hoarse.auction.web.dto.member.MemberDto;
import com.hoarse.auction.web.dto.member.MemberRequest;

import java.util.List;

public interface MemberService {

    MemberDto createUser(MemberRequest memberRequest);

    MemberDto findUser(String email);

    MemberDto findByEmailAndPassword(String email, String password);

    List<MemberDto> findAll();
}
