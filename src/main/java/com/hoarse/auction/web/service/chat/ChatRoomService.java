package com.hoarse.auction.web.service.chat;

import com.hoarse.auction.web.entity.chat.ChatRoom;
import com.hoarse.auction.web.repository.chat.ChatRepository;

import com.hoarse.auction.web.repository.chat.ChatroomRepository;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Data
@Service
@RequiredArgsConstructor
public class ChatRoomService {
    //    private final ObjectMapper mapper;
    private Map<Long, ChatRoom> chatRooms;

    private final ChatroomRepository chatroomRepository;

    @PostConstruct
    //의존관게 주입완료되면 실행되는 코드
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }

    //채팅방 불러오기
    public List<ChatRoom> findAllRoom() {
        //채팅방 최근 생성 순으로 반환

        List<ChatRoom> result = new ArrayList<>(chatRooms.values());
        result = chatroomRepository.findAll();
        Collections.reverse(result);

        return result;
    }

    //채팅방 하나 불러오기
    public ChatRoom findById(String roomId) {
        return chatRooms.get(roomId);
    }

    //채팅방 생성
    public ChatRoom createRoom(String name) {
        ChatRoom chatRoom = ChatRoom.create(name);
        chatRooms.put(chatRoom.getRoomId(), chatRoom);

        chatroomRepository.save(chatRoom); // 채팅방 저장
        return chatRoom;
    }

}
