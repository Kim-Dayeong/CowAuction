package com.hoarse.auction.web.serviceImpl.hoarse;

import com.hoarse.auction.web.dto.horse.HorseDto;
import com.hoarse.auction.web.dto.horse.HorseRequestDto;
import com.hoarse.auction.web.dto.horse.HorseResponseDto;
import com.hoarse.auction.web.dto.horse.HorseupdateDto;
import com.hoarse.auction.web.entity.horse.Horse;
import com.hoarse.auction.web.entity.member.Member;
import com.hoarse.auction.web.module.RandomMaker;
import com.hoarse.auction.web.repository.hoarse.HorseRepository;
import com.hoarse.auction.web.repository.member.MemberRepository;
import com.hoarse.auction.web.service.horse.HorseService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HorseServiceImpl implements HorseService {

    private final HorseRepository horseRepository;
    private final MemberRepository memberRepository;

    @Override
    public HorseDto registerHoarse(HorseRequestDto hoarseRequest){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member member = memberRepository.findById(Long.valueOf(authentication.getName())).orElseThrow(()-> new BadCredentialsException("회원 정보를 찾을 수 없습니다."));
        Horse hoarse = horseRepository.save(
                Horse.builder()
                        .birth(hoarseRequest.getBirth()).name(hoarseRequest.getName()).furcolor(hoarseRequest.getFurcolor()).uniqueNum(RandomMaker.func())// 랜덤값 예외처리
                        .owner(hoarseRequest.getOwner()).producer(member).mother(hoarseRequest.getMother()).father(hoarseRequest.getFather())
                        .build());
            return HorseDto.builder().id(hoarse.getId()).name(hoarse.getName()).furcolor(hoarse.getFurcolor())
                    .uniqueNum(hoarse.getUniqueNum())
                    .birth(hoarse.getBirth())
                    .owner(hoarse.getOwner()).producer(hoarse.getProducer()).father(hoarse.getFather()).mother(hoarse.getMother())
                    .build();
    }
    // 말 수정
    @Override
    public HorseDto updateHoarse(Long hoarseId, HorseupdateDto requestDto, Member member) {

        Horse hoarse = horseRepository.findById(hoarseId).orElseThrow(()-> new EntityNotFoundException("말 정보를 찾을 수 없습니다."));
        if(!hoarse.getProducer().getUsername().equals(member.getUsername())){

            throw new AccessDeniedException("회원 정보가 일치하지 않습니다.");
        }

        hoarse.setOwner(requestDto.getOwner());
        hoarse.setFather(requestDto.getFather());
        hoarse.setMother(requestDto.getMother());
        hoarse.setName(requestDto.getName());

        hoarse.setFurcolor(requestDto.getFurcolor());
        horseRepository.save(hoarse);

        return HorseDto.builder()
                .id(hoarse.getId())
                .owner(requestDto.getOwner())
                .father(requestDto.getFather())
                .mother(requestDto.getMother())
                .name(requestDto.getName())
                .furcolor(requestDto.getFurcolor())
                .build();



    }

    @Override
    public void deleteHorse(Long hoarseId, Member member){

        Horse hoarse = horseRepository.findById(hoarseId).orElseThrow(()-> new EntityNotFoundException("말 정보를 찾을 수 없습니다."));
        if(!hoarse.getProducer().getUsername().equals(member.getUsername())) {
            throw new AccessDeniedException("회원 정보가 일치하지 않습니다.");
        }
        horseRepository.delete(hoarse);
    }


    @Override
    public HorseDto findHoarse(Long hoarseId){
        Horse hoarse = horseRepository.findById(hoarseId).orElseThrow(()-> new BadCredentialsException("말 정보를 찾을 수 없습니다."));
        return HorseDto.builder().id(hoarse.getId()).name(hoarse.getName()).furcolor(hoarse.getFurcolor())
                .uniqueNum(hoarse.getUniqueNum())
                .birth(hoarse.getBirth())
                .owner(hoarse.getOwner()).producer(hoarse.getProducer()).father(hoarse.getFather()).mother(hoarse.getMother())
                .build();

    }

    @Override
    public HorseDto findHoarsename(String name){ // 중복될 경우 예외 수정
        Horse hoarse = Optional.ofNullable(horseRepository.findByName(name)).orElseThrow(() -> new BadCredentialsException("말 정보를 찾을 수 없습니다."));
        return HorseDto.builder().id(hoarse.getId()).name(hoarse.getName()).furcolor(hoarse.getFurcolor())
                .uniqueNum(hoarse.getUniqueNum())
                .birth(hoarse.getBirth())
                .owner(hoarse.getOwner()).producer(hoarse.getProducer()).father(hoarse.getFather()).mother(hoarse.getMother())
                .build();

    }

    @Override
    public List<HorseResponseDto> hoarseList(){
        List<Horse> hoarses = horseRepository.findAll();
        List<HorseResponseDto> hoarselist = new ArrayList<>();

        for (Horse hoarse : hoarses){
            HorseResponseDto hoarseDto = new HorseResponseDto(hoarse);
            hoarselist.add(hoarseDto);
        }

        return hoarselist;

    }


}
