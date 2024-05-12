package com.hoarse.auction.web.controller.member;

import com.hoarse.auction.web.config.jwt.JwtAuthenticationFilter;
import com.hoarse.auction.web.config.jwt.JwtConfig;

import com.hoarse.auction.web.config.security.SecurityUser;
import com.hoarse.auction.web.dto.jwt.JwtResponseDTO;
import com.hoarse.auction.web.dto.member.updateResponseMemberDto;
import com.hoarse.auction.web.dto.member.MemberDto;
import com.hoarse.auction.web.dto.member.MemberRequestDto;

import com.hoarse.auction.web.entity.member.Member;
import com.hoarse.auction.web.service.auth.AuthService;
import com.hoarse.auction.web.service.horse.HorseService;
import com.hoarse.auction.web.service.member.MemberService;
import com.hoarse.auction.web.serviceImpl.hoarse.HorseServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequestMapping("api/member")
@RequiredArgsConstructor
//@ComponentScan(basePackages = {"com.hoarse.auction.web.config.jwt"})
public class MemberController {

    private final MemberService memberService;
//    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthService authService;
    private final JwtConfig jwtConfig;
    private final HorseServiceImpl horseService;

    @GetMapping("/info")
    public String getMemberInfo( @AuthenticationPrincipal SecurityUser principal){

        if (principal != null) {
            return "true:"+principal.getMember().getUsername();
        }
        return "null";

//        String token = jwtConfig.resolveToken(request);
//
//        if(token != null && jwtConfig.validateToken(token)){
//            // 토큰이 유효하면
//            String username = jwtConfig.getAuthentication(token).getName();
//            return username;
//        }
//
//        return "토큰이 유효하지 않습니다";

    }


    @PostMapping("/signup")
    public MemberDto createUser(@RequestBody MemberRequestDto memberRequestDto) {
        return memberService.createUser(memberRequestDto);
    }



    @PostMapping("/login")
    public ResponseEntity<JwtResponseDTO> login(@RequestBody MemberRequestDto memberRequestDto,JwtResponseDTO jwtResponseDTO) {
        // 사용자 정보 확인 및 토큰 생성
        MemberDto member = memberService.findByEmailAndPassword(memberRequestDto.getUsername(), memberRequestDto.getPassword());
       jwtResponseDTO = jwtConfig.createToken(member.getUsername(), Arrays.asList(member.getRole().getValue()));

        // 토큰을 응답으로 반환
        return ResponseEntity.ok(jwtResponseDTO);
    }



    @GetMapping("/admin")
    @PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
    public List<MemberDto> findAllUser() {
        return memberService.findAll();
    }



    @GetMapping("/my")
    public String findUser(@RequestHeader(AUTHORIZATION)String token) {
        if (token == null) {
            throw new BadCredentialsException("회원 정보를 찾을 수 없습니다.(로그인 안됨)");
        }
        String member = authService.getMemberFromToken(token).getUsername();
        return member;
    }

    @GetMapping("/my/horse/exel")
    public void horseExport(HttpServletResponse response) throws IOException {  //엑셀로 말 목록 내보내기


        horseService.hoarseList();

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
        cell.setCellValue("생산자");

        // Body
        for (int i=0; i<3; i++) {
            row = sheet.createRow(rowNum++);
            cell = row.createCell(0);
            cell.setCellValue(i);
            cell = row.createCell(1);
            cell.setCellValue(i+"_name");
            cell = row.createCell(2);
            cell.setCellValue(i+"_title");
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
