package com.hoarse.auction.web.controller.hoarse;

import com.hoarse.auction.web.config.security.SecurityUser;
import com.hoarse.auction.web.dto.hoarse.HoarseDto;
import com.hoarse.auction.web.dto.hoarse.HoarseRequest;
import com.hoarse.auction.web.dto.hoarse.HoarseResponseDTO;
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
    @PostMapping("/register")
    public HoarseDto registerHoarse(@RequestBody  HoarseRequest hoarseRequest, @AuthenticationPrincipal SecurityUser principal){

        hoarseRequest.setProducer(principal.getMember());
        return hoarseService.registerHoarse(hoarseRequest);
    }

    // 말 리스트 (전체보기)
    @GetMapping("/list")
    public List<HoarseResponseDTO> list(){
        return hoarseService.hoarseList();
    }

    // 말 검색
    @GetMapping("/search")
    private HoarseDto findHoarse(String name){
        return hoarseService.findHoarse(name);
    }

    // 소유주명으로 말 검색



    // 생산자명으로 말 검색

}
