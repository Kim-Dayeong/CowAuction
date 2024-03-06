package com.hoarse.auction.web.service.hoarse;

import com.hoarse.auction.web.config.security.SecurityUser;
import com.hoarse.auction.web.dto.hoarse.HoarseDto;
import com.hoarse.auction.web.dto.hoarse.HoarseRequestDto;
import com.hoarse.auction.web.dto.hoarse.HoarseResponseDto;
import com.hoarse.auction.web.entity.member.Member;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;

public interface HoarseService {

    // 말 등록
    HoarseDto registerHoarse(HoarseRequestDto hoarseRequest);

    // 말 전체 리스트
    List<HoarseResponseDto> hoarseList();


    // 말 수정
    String updateHoarse(Long hoarseId, HoarseRequestDto requestDto, Member member);

    // 말 상세보기
    HoarseDto findHoarse(Long hoarseId);

    // 말 이름으로 검색
    HoarseDto findHoarsename(String name);

    // 소유주명으로 말 검색



    // 생산자명으로 말 검색



    // 말 삭제
    String deleteHoarse(Long hoarseId, Member member);


}
