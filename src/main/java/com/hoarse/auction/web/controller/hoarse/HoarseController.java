package com.hoarse.auction.web.controller.hoarse;

import com.hoarse.auction.web.dto.hoarse.HoarseDto;
import com.hoarse.auction.web.dto.hoarse.HoarseRequest;
import com.hoarse.auction.web.service.hoarse.HoarseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/Hoarse")
@RequiredArgsConstructor
public class HoarseController {

    private final HoarseService hoarseService;

    // 말 등록
    @PostMapping("/register")
    public HoarseDto registerHoarse(HoarseRequest hoarseRequest){
        return hoarseService.registerHoarse(hoarseRequest);
    }

    // 말 검색
    @GetMapping("/search")
    private HoarseDto findHoarse(String name){
        return hoarseService.findHoarse(name);
    }

    // 소유주명으로 말 검색



    // 생산자명으로 말 검색

}
