package com.hoarse.auction.web.controller.member;

import com.hoarse.auction.web.dto.member.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/register")
    public ReseponseEntity<MemberRegisterResponse> register(@RequestBody MemberRegisterRequest){
        try{
            MemberDto memberDto = memberService.register(memberRegisterRequest);
            return new ResponseEntity<>(new MemberRegisterResponse(memberDto.getEmployName()), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/login")
    public ReseponseEntity<MemberRegisterResponse> register(@RequestBody MemberRegisterRequeset memberRegisterRequeset)
    {
        MemberDto memberDto = memberService.register(memberRegisterRequeset);
        return new ResponseEntity<>(new MemberRegisterResponse(memberDto.getEmployName()), HttpStatus.OK);
    }


}
