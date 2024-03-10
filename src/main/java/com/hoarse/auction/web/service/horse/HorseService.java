package com.hoarse.auction.web.service.horse;

import com.hoarse.auction.web.dto.horse.HorseDto;
import com.hoarse.auction.web.dto.horse.HorseRequestDto;
import com.hoarse.auction.web.dto.horse.HorseResponseDto;
import com.hoarse.auction.web.entity.member.Member;

import java.util.List;

public interface HorseService {

    // 말 등록
    HorseDto registerHoarse(HorseRequestDto hoarseRequest);

    // 말 전체 리스트
    List<HorseResponseDto> hoarseList();


    // 말 수정
    HorseDto updateHoarse(Long hoarseId, HorseRequestDto requestDto, Member member);

    // 말 상세보기
    HorseDto findHoarse(Long hoarseId);

    // 말 이름으로 검색
    HorseDto findHoarsename(String name);

    // 소유주명으로 말 검색



    // 생산자명으로 말 검색



    // 말 삭제
    void deleteHoarse(Long hoarseId, Member member);


}
