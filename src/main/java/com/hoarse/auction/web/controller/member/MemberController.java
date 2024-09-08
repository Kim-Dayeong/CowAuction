package com.hoarse.auction.web.controller.member;

import com.hoarse.auction.web.config.jwt.JwtAuthenticationFilter;
import com.hoarse.auction.web.config.jwt.JwtConfig;

import com.hoarse.auction.web.config.security.SecurityUser;
import com.hoarse.auction.web.dto.horse.HorseRequestDto;
import com.hoarse.auction.web.dto.horse.HorseResponseDto;
import com.hoarse.auction.web.dto.jwt.JwtResponseDTO;
import com.hoarse.auction.web.dto.member.updateResponseMemberDto;

import com.hoarse.auction.web.entity.member.Member;
import com.hoarse.auction.web.service.auth.AuthService;
import com.hoarse.auction.web.service.horse.HorseService;
import com.hoarse.auction.web.service.member.MemberService;
import com.hoarse.auction.web.serviceImpl.hoarse.HorseServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequestMapping("api/member")
@RequiredArgsConstructor
@Tag(name = "회원 API")
//@ComponentScan(basePackages = {"com.hoarse.auction.web.config.jwt"})
public class MemberController {

    private final MemberService memberService;
    private final AuthService authService;
    private final JwtConfig jwtConfig;
    private final HorseServiceImpl horseService;

    @GetMapping("/info")
    public String getMemberInfo( @AuthenticationPrincipal SecurityUser principal){

        if (principal != null) {
            return "true:"+principal.getMember().getUsername();
        }
        return "null";



    }

    @Operation(summary = "회원가입 API")
    @PostMapping("/signup")
    public MemberDto createUser(@RequestBody MemberRequestDto memberRequestDto) {
        return memberService.createUser(memberRequestDto);
    }


    @Operation(summary = "로그인 API")
    @PostMapping("/login")
    public ResponseEntity<JwtResponseDTO> login(@RequestBody MemberRequestDto memberRequestDto,JwtResponseDTO jwtResponseDTO) {
        // 사용자 정보 확인 및 토큰 생성
        MemberDto member = memberService.findByEmailAndPassword(memberRequestDto.getUsername(), memberRequestDto.getPassword());
       jwtResponseDTO = jwtConfig.createToken(member.getUsername(), Collections.singletonList(member.getRole().getValue()));

        // 토큰을 응답으로 반환
        return ResponseEntity.ok(jwtResponseDTO);
    }




    @Operation(summary = "어드민 - 모든 회원 조회 API")
    @GetMapping("/admin")
    @PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
    public List<MemberDto> findAllUser() {
        return memberService.findAll();
    }




    @Operation(summary = "로그인 회원 정보 API")
    @GetMapping("/my")
    public String findUser(@RequestHeader(AUTHORIZATION)String token) {
        if (token == null) {
            throw new BadCredentialsException("회원 정보를 찾을 수 없습니다.(로그인 안됨)");
        }
        String member = authService.getMemberFromToken(token).getUsername();
        return member;
    }


    @Operation(summary = "회원 소유 말목록 엑셀 내보내기 API")
    @GetMapping("/my/horse/exel")
    public void horseExport(HttpServletResponse response) throws IOException {  //엑셀로 말 목록 내보내기


        List<HorseResponseDto>  horseList =  horseService.hoarseList();

        Workbook wb = new XSSFWorkbook();

        Sheet sheet = wb.createSheet("내 소유 말 목록");
        Row row = null;
        Cell cell = null;
        int rowNum = 0;

        // Header
        row = sheet.createRow(rowNum++);
        cell = row.createCell(0);
        cell.setCellValue("번호");
        cell = row.createCell(1);
        cell.setCellValue("마 명");
        cell = row.createCell(2);
        cell.setCellValue("소유주");
        cell = row.createCell(3);
        cell.setCellValue("생산자");
        cell = row.createCell(4);
        cell.setCellValue("고유번호");


        // Body
        for(int i=0; i<horseList.size(); i++){

                row = sheet.createRow(rowNum++);
                cell = row.createCell(0);
                HorseResponseDto horse =  horseList.get(i);
            cell.setCellValue(i);
            cell = row.createCell(1);
            cell.setCellValue(horse.getName());
            cell = row.createCell(2);
            if(horse.getOwner() != null){
                cell.setCellValue(String.valueOf(horse.getOwner().getName()));
            }
            cell.setCellValue("");
            cell = row.createCell(3);
            if(horse.getProducer() != null){
                cell.setCellValue(String.valueOf(horse.getProducer().getName()));
            }
            cell.setCellValue("");

            cell = row.createCell(4);
            cell.setCellValue(String.valueOf(horse.getUniqueNum()));

        }


        // 컨텐츠 타입과 파일명 지정
        response.setContentType("ms-vnd/excel");
//        response.setHeader("Content-Disposition", "attachment;filename=example.xls");
        response.setHeader("Content-Disposition", "attachment;filename=example.xlsx");

        // Excel File Output
        wb.write(response.getOutputStream());
        wb.close();
    }





    // 회원탈퇴
    @Operation(summary = "회원 탈퇴 API")
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteMember(@RequestHeader(AUTHORIZATION)String token){
        if (token == null) {
            throw new BadCredentialsException("회원 정보를 찾을 수 없습니다.(로그인 안됨)");
        }

       memberService.deleteMember(token);

       return ResponseEntity.ok().build();
    }

    //회원 수정
//    @PutMapping("/update/{memberId}")
//    public updateResponseMemberDto updateMember(MemberRequestDto memberDto,
//            @PathVariable("memberId") Long memberId,@AuthenticationPrincipal SecurityUser principal){
//         MemberDto updatemamber = memberService.updateMember(memberId,memberDto, principal.getMember());
//        return new updateResponseMemberDto(updatemamber.getName(),updatemamber.getPhone(),updatemamber.getPassword());
//    }



}
