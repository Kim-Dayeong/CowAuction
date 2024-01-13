package com.hoarse.auction.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class testcontroller {

    @GetMapping("/test")
    ResponseEntity<?> test() {
        return ResponseEntity.ok("테스트 입니다");
    }
}
