package com.hoarse.auction.web.serviceImpl.hoarse;

import com.hoarse.auction.web.config.security.SecurityUser;
import com.hoarse.auction.web.dto.hoarse.HoarseDto;
import com.hoarse.auction.web.dto.hoarse.HoarseRequestDto;
import com.hoarse.auction.web.dto.hoarse.HoarseResponseDto;
import com.hoarse.auction.web.entity.hoarse.Hoarse;
import com.hoarse.auction.web.entity.member.Member;
import com.hoarse.auction.web.module.RandomMaker;
import com.hoarse.auction.web.repository.hoarse.HoarseRepository;
import com.hoarse.auction.web.service.hoarse.HoarseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HoarseServiceImpl implements HoarseService {

    private final HoarseRepository hoarseRepository;

    @Override
    public HoarseDto registerHoarse(HoarseRequestDto hoarseRequest){
        Hoarse hoarse = hoarseRepository.save(
                Hoarse.builder()
                        .birth(hoarseRequest.getBirth()).name(hoarseRequest.getName()).furcolor(hoarseRequest.getFurcolor()).uniqueNum(RandomMaker.func())// 랜덤값 예외처리
                        .owner(hoarseRequest.getOwner()).producer(hoarseRequest.getProducer()).mother(hoarseRequest.getMother()).father(hoarseRequest.getFather())
                        .build());
            return HoarseDto.builder().id(hoarse.getId()).name(hoarse.getName()).furcolor(hoarse.getFurcolor())
                    .uniqueNum(hoarse.getUniqueNum())
                    .birth(hoarse.getBirth())
                    .owner(hoarse.getOwner()).producer(hoarse.getProducer()).father(hoarse.getFather()).mother(hoarse.getMother())
                    .build();
    }
    // 말 수정
    @Override
    public String updateHoarse(Long hoarseId, HoarseRequestDto requestDto, Member member) {
        String message = "fail";
        Hoarse hoarse = hoarseRepository.findById(hoarseId).orElseThrow(()-> new BadCredentialsException("말 정보를 찾을 수 없습니다."));
        if(hoarse.getProducer().getUsername().equals(member.getUsername())){
            hoarse.setOwner(requestDto.getOwner());
            hoarse.setFather(requestDto.getFather());
            hoarse.setMother(requestDto.getMother());
            hoarse.setName(requestDto.getName());
            hoarse.setProducer(requestDto.getProducer());
            hoarse.setFurcolor(requestDto.getFurcolor());
            hoarseRepository.save(hoarse);
            message = "success";
        }

        return message; // 방식 수정

    }

    @Override
    public String deleteHoarse(Long hoarseId, Member member){
        String message = "fail";
        Hoarse hoarse = hoarseRepository.findById(hoarseId).orElseThrow(()-> new BadCredentialsException("말 정보를 찾을 수 없습니다."));
        if(hoarse.getProducer().getUsername().equals(member.getUsername())) {
            hoarseRepository.delete(hoarse);
            message = "success";
        }

        return message;

    }


    @Override
    public HoarseDto findHoarse(Long hoarseId){
        Hoarse hoarse = hoarseRepository.findById(hoarseId).orElseThrow(()-> new BadCredentialsException("말 정보를 찾을 수 없습니다."));
        return HoarseDto.builder().id(hoarse.getId()).name(hoarse.getName()).furcolor(hoarse.getFurcolor())
                .uniqueNum(hoarse.getUniqueNum())
                .birth(hoarse.getBirth())
                .owner(hoarse.getOwner()).producer(hoarse.getProducer()).father(hoarse.getFather()).mother(hoarse.getMother())
                .build();

    }

    @Override
    public HoarseDto findHoarsename(String name){ // 중복될 경우 예외 수정
        Hoarse hoarse = Optional.ofNullable(hoarseRepository.findByName(name)).orElseThrow(() -> new BadCredentialsException("말 정보를 찾을 수 없습니다."));
        return HoarseDto.builder().id(hoarse.getId()).name(hoarse.getName()).furcolor(hoarse.getFurcolor())
                .uniqueNum(hoarse.getUniqueNum())
                .birth(hoarse.getBirth())
                .owner(hoarse.getOwner()).producer(hoarse.getProducer()).father(hoarse.getFather()).mother(hoarse.getMother())
                .build();

    }

    @Override
    public List<HoarseResponseDto> hoarseList(){
        List<Hoarse> hoarses = hoarseRepository.findAll();
        List<HoarseResponseDto> hoarselist = new ArrayList<>();

        for (Hoarse hoarse : hoarses){
            HoarseResponseDto hoarseDto = new HoarseResponseDto(hoarse);
            hoarselist.add(hoarseDto);
        }

        return hoarselist;

    }


}
