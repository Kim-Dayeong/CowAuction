package com.hoarse.auction.web.controller.member;

import com.hoarse.auction.web.dto.member.request.MemberSignUpRequest;

import com.hoarse.auction.web.service.member.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    @ResponseStatus(HttpStatus.OK)
    public Long join(@Valid @RequestBody MemberSignUpRequest request) throws Exception {
        return memberService.signUp(request);
    }
}
