package com.jobPrize.admin01_service.service;

import java.util.List;

import com.jobPrize.admin01_service.dto.ChatRequestDto;
import com.jobPrize.admin01_service.dto.ChatResponseDto;
import com.jobPrize.entity.common.ChatRoom;

public interface ChatService {
    List<ChatRoom> selectChatRooms() throws Exception;
	List<ChatResponseDto> selectMessages() throws Exception;
	void insertMessage(ChatRequestDto chatRequestDto) throws Exception;
}