package com.hoarse.auction.web.controller.hoarse;

import com.hoarse.auction.web.config.security.SecurityUser;
import com.hoarse.auction.web.dto.hoarse.HoarseDto;
import com.hoarse.auction.web.dto.hoarse.HoarseRequestDto;
import com.hoarse.auction.web.dto.hoarse.HoarseResponseDto;
import com.hoarse.auction.web.service.hoarse.HoarseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hoarse")
@RequiredArgsConstructor
public class HoarseController {

    private final HoarseService hoarseService;

    // 말 등록
    @PostMapping("/create")
    public HoarseDto registerHoarse(@RequestBody HoarseRequestDto hoarseRequest, @AuthenticationPrincipal SecurityUser principal){

        hoarseRequest.setProducer(principal.getMember());
        return hoarseService.registerHoarse(hoarseRequest);
    }

    // 말 리스트 (전체보기)
    @GetMapping("/list")
    public List<HoarseResponseDto> list(){
        return hoarseService.hoarseList();
    }

    // 말 정보 상세보기
    @GetMapping("/read/{hoarseId}")
    private HoarseDto findHoarse(@PathVariable Long hoarseId){
        return hoarseService.findHoarse(hoarseId);
    }

    // 말 이름으로 검색
    @GetMapping("/search/{name}")
    private HoarseDto searchHoarse(@PathVariable String name){
        return hoarseService.findHoarsename(name);
    }

    // 소유주명으로 말 검색



    // 생산자명으로 말 검색


    // 말 삭제
    @DeleteMapping("/delete/{hoarseId}")
    public String deleteHoarse(@PathVariable("hoarseId") Long hoarseId,
                                        @AuthenticationPrincipal SecurityUser principal){


                return hoarseService.deleteHoarse(hoarseId, principal.getMember());
    }

    // 말 수정
    @PutMapping("/update/{hoarseId}")
    public String update(@PathVariable Long hoarseId, @RequestBody HoarseRequestDto requestDto, @AuthenticationPrincipal SecurityUser principal){

        return hoarseService.updateHoarse(hoarseId, requestDto, principal.getMember());
    }

}
