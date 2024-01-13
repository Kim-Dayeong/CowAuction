package com.hoarse.auction.web.service.hoarse;

import com.hoarse.auction.web.dto.hoarse.HoarseDto;
import com.hoarse.auction.web.dto.hoarse.HoarseRequest;
import com.hoarse.auction.web.entity.hoarse.Hoarse;

public interface HoarseService {

    // 말 등록
    HoarseDto registerHoarse(HoarseRequest hoarseRequest);

    // 말 검색
    HoarseDto findHoarse(String hoarseName);

    // 소유주명으로 말 검색



    // 생산자명으로 말 검색


}
