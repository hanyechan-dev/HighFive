package com.jobPrize.admin01_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobPrize.admin01_service.dto.ChatRequestDto;
import com.jobPrize.admin01_service.dto.ChatResponseDto;
import com.jobPrize.entity.common.ChatRoom;
import com.jobPrize.repository.common.chatRoom.ChatRoomRepositoryImpl;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    ChatRoomRepositoryImpl chatRoomRepositoryImpl;

    @Override
    public List<ChatRoom> selectChatRooms() throws Exception {
        // 토큰으로부터 ID를 받아서 채팅방 리스트 조회
        // 어차피 출력하는 기능일 거라 return을 void로 해도 될 듯?
        List<ChatRoom> chatRooms = chatRoomRepositoryImpl.findAllByUserId(Long id);
        return chatRooms;
    }
	
	@Override
	public List<ChatResponseDto> selectMessages() throws Exception {
        
	}

	@Override
	public void insertMessage(ChatRequestDto chatRequestDto) throws Exception {

    }