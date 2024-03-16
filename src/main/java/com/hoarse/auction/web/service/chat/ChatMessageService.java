package com.hoarse.auction.web.service.chat;

import com.hoarse.auction.web.dto.chat.ChatMessageDto;
import com.hoarse.auction.web.repository.chat.ChatMessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ChatMessageService {

   private final ChatMessageRepository chatMessageRepository;

   // 채팅 저장



    //채팅 불러오기
    public List<ChatMessageDto> loadCha(String roomId){
        List<ChatMessageDto> messaeList = new ArrayList<>();

        //redis에서 해당 채팅방 메시지 50개 가져오기 + 만약 redis에 저장된 채팅있으면 밑에 추가로 가져오기
        List<>
    }
}
