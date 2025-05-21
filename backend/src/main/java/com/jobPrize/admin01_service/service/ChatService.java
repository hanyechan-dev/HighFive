package com.jobPrize.admin01_service.service;

import java.util.List;

import com.jobPrize.admin01_service.dto.ChatRequestDto;
import com.jobPrize.admin01_service.dto.ChatResponseDto;
import com.jobPrize.entity.common.ChatRoom;

public interface ChatService {
	void createMessage(ChatRequestDto chatRequestDto) throws Exception;
    List<ChatRoom> readChatRoomsList(String authorizationHeader) throws Exception;
	List<ChatResponseDto> readMessagesList(Long roomId) throws Exception;
}