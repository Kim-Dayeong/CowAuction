package com.hoarse.auction.web.controller.horse;

import com.hoarse.auction.web.config.security.SecurityUser;
import com.hoarse.auction.web.dto.horse.HorseDto;
import com.hoarse.auction.web.dto.horse.HorseRequestDto;
import com.hoarse.auction.web.dto.horse.HorseResponseDto;
import com.hoarse.auction.web.entity.horse.Horse;
import com.hoarse.auction.web.service.horse.HorseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/horse")
@RequiredArgsConstructor
public class HorseController {

    private final HorseService horseService;

    // 말 등록
    @PostMapping("/create")
    public HorseDto registerHoarse(@RequestBody HorseRequestDto hoarseRequest){

        return horseService.registerHoarse(hoarseRequest);
    }

    // 말 리스트 (전체보기)
    @GetMapping("/list")
    public List<HorseResponseDto> list(){
        return horseService.hoarseList();
    }

    // 말 정보 상세보기
    @GetMapping("/read/{horseId}")
    private HorseDto findHoarse(@PathVariable Long hoarseId){
        return horseService.findHoarse(hoarseId);
    }

    // 말 이름으로 검색
    @GetMapping("/search/{name}")
    private HorseDto searchHoarse(@PathVariable String name){
        return horseService.findHoarsename(name);
    }

    // 소유주명으로 말 검색



    // 생산자명으로 말 검색


    // 말 삭제
    @DeleteMapping("/delete/{horseId}")
    public ResponseEntity<?> deleteHoarse(@PathVariable("hoarseId") Long hoarseId,
                                       @AuthenticationPrincipal SecurityUser principal){
        horseService.deleteHorse(hoarseId, principal.getMember());
                return ResponseEntity.ok().build();
    }

    // 말 수정
    @PutMapping("/update/{horseId}")
    public HorseDto update(@PathVariable Long hoarseId, @RequestBody HorseRequestDto requestDto, @AuthenticationPrincipal SecurityUser principal){

        return horseService.updateHoarse(hoarseId, requestDto, principal.getMember());
    }

}
