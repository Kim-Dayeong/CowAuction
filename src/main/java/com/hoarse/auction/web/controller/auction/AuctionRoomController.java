package com.hoarse.auction.web.controller.auction;


import com.hoarse.auction.web.entity.auction.AuctionRoom;
import com.hoarse.auction.web.service.auction.AuctionRoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auction")
@Tag(name = "경매방 API")
public class AuctionRoomController {

    private final AuctionRoomService auctionRoomService;


    @Operation(summary = "경매 리스트 화면 API")
    @GetMapping("/room")
    public String rooms(Model model) {
        return "/auction/room";
    }


    @Operation(summary = "모든 경매방 리스트 API")
    @GetMapping("/rooms")
    @ResponseBody
    public List<AuctionRoom> room() {
        return auctionRoomService.findAllRoom();
    }


    @Operation(summary = "경매방 생성 API")
    @PostMapping("/room")
    @ResponseBody
    public AuctionRoom createRoom(
            @RequestParam String name,
                                  @RequestParam String hoarseId) {

        System.out.println("아이디!!!!!"+hoarseId);
        return auctionRoomService.createRoom(name, hoarseId);
    }

    @Operation(summary = "경매방 입장 API")
    @GetMapping("/room/enter/{roomId}")
    public String roomDetail(Model model, @PathVariable String roomId) {
        model.addAttribute("roomId", roomId);


        return "/auction/roomdetail";
    }

    @Operation(summary = "특정 경매방 조회 API")
    @GetMapping("/room/{roomId}")
    @ResponseBody
    public AuctionRoom roomInfo(@PathVariable String roomId) {
        return auctionRoomService.findById(roomId);
    }
}


