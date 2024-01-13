package com.hoarse.auction.web.serviceImpl.hoarse;

import com.hoarse.auction.web.dto.hoarse.HoarseDto;
import com.hoarse.auction.web.dto.hoarse.HoarseRequest;
import com.hoarse.auction.web.entity.hoarse.Hoarse;
import com.hoarse.auction.web.repository.hoarse.HoarseRepository;
import com.hoarse.auction.web.service.hoarse.HoarseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HoarseServiceImpl implements HoarseService {

    private final HoarseRepository hoarseRepository;

    @Override
    public HoarseDto registerHoarse(HoarseRequest hoarseRequest){
        Hoarse hoarse = hoarseRepository.save(
                Hoarse.builder()
                        .birth(hoarseRequest.getBirth()).name(hoarseRequest.getName()).furcolor(hoarseRequest.getFurcolor())
                        .owner(hoarseRequest.getOwner()).producer(hoarseRequest.getProducer()).mother(hoarseRequest.getMother()).father(hoarseRequest.getFather())
                        .build());
            return HoarseDto.builder().id(hoarse.getId()).name(hoarse.getName()).furcolor(hoarse.getFurcolor())
//                    .uuid(hoarse.getUuid())
                    .birth(hoarse.getBirth())
                    .owner(hoarse.getOwner()).producer(hoarse.getProducer()).father(hoarse.getFather()).mother(hoarse.getMother())
                    .build();
    }

    @Override
    public HoarseDto findHoarse(String name){ // 중복될 경우 예외 수정
        Hoarse hoarse = Optional.ofNullable(hoarseRepository.findByName(name)).orElseThrow(() -> new BadCredentialsException("말 정보를 찾을 수 없습니다."));
        return HoarseDto.builder().id(hoarse.getId()).name(hoarse.getName()).furcolor(hoarse.getFurcolor())
//                .uuid(hoarse.getUuid())
                .birth(hoarse.getBirth())
                .owner(hoarse.getOwner()).producer(hoarse.getProducer()).father(hoarse.getFather()).mother(hoarse.getMother())
                .build();

    }
}
