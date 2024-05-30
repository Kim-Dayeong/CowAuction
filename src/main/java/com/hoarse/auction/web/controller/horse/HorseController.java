package com.hoarse.auction.web.controller.horse;

import com.hoarse.auction.web.config.security.SecurityUser;
import com.hoarse.auction.web.dto.horse.HorseDto;
import com.hoarse.auction.web.dto.horse.HorseRequestDto;
import com.hoarse.auction.web.dto.horse.HorseResponseDto;
import com.hoarse.auction.web.dto.horse.HorseupdateDto;
import com.hoarse.auction.web.entity.horse.Horse;
import com.hoarse.auction.web.service.horse.HorseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/horse")
@RequiredArgsConstructor
@Tag(name = "말 API")
public class HorseController {

    private final HorseService horseService;

    @Operation(summary = "말 등록 API")

    @PostMapping("/create")
    public HorseDto registerHoarse(@RequestBody HorseRequestDto hoarseRequest){

        return horseService.registerHoarse(hoarseRequest);
    }

    @Operation(summary = "말 전체 리스트 API")
    @GetMapping("/list")
    public List<HorseResponseDto> list(){
        return horseService.hoarseList();
    }

    @Operation(summary = "말 정보 상세보기 API")
    @GetMapping("/read/{horseId}")
    private HorseDto findHoarse(@PathVariable Long horseId){
        return horseService.findHoarse(horseId);
    }

    @Operation(summary = "말 이름으로 검색 API")
    @GetMapping("/search/{name}")
    private HorseDto searchHoarse(@PathVariable String name){
        return horseService.findHoarsename(name);
    }

    // 소유주명으로 말 검색



    // 생산자명으로 말 검색


    @Operation(summary = "말 삭제 API")
    @DeleteMapping("/delete/{horseId}")
    public ResponseEntity<?> deleteHoarse(@PathVariable("horseId") Long horseId,
                                       @AuthenticationPrincipal SecurityUser principal){
        horseService.deleteHorse(horseId, principal.getMember());
        return ResponseEntity.ok("말 정보가 성공적으로 삭제되었습니다.");
    }

    @Operation(summary = "말 수정 API")
    @PutMapping("/update/{horseId}")
    public HorseDto update(@PathVariable Long horseId, @RequestBody HorseupdateDto requestDto, @AuthenticationPrincipal SecurityUser principal){

        return horseService.updateHoarse(horseId, requestDto, principal.getMember());
    }

}
