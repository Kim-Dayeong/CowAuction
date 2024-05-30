package com.hoarse.auction.web.controller.chat;

import com.hoarse.auction.web.entity.chat.ChatRoom;
import com.hoarse.auction.web.service.chat.ChatRoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
@Tag(name = "채팅룸 API")
public class ChatRoomController {
    private final ChatRoomService chatRoomService;

    @Operation(summary = "채팅 리스트 화면 API")
    @GetMapping("/room")
    public String rooms(Model model) {
        return "/chat/room";
    }


    @Operation(summary = "모든 채팅 목록 반환 API")
    @GetMapping("/rooms")
    @ResponseBody
    public List<ChatRoom> room() {
        return chatRoomService.findAllRoom();
    }


    @Operation(summary = "채팅방 생성 API")
    @PostMapping("/room")
    @ResponseBody
    public ChatRoom createRoom(@RequestParam String name) {
        return chatRoomService.createRoom(name);
    }

    @Operation(summary = "채팅방 입장 화면 API")
    @GetMapping("/room/enter/{roomId}")
    public String roomDetail(Model model, @PathVariable String roomId) {
        model.addAttribute("roomId", roomId);
        return "/chat/roomdetail";
    }

    @Operation(summary = "특정 채팅방 조회 API")
    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ChatRoom roomInfo(@PathVariable String roomId) {
        return chatRoomService.findById(roomId);
    }
}