package com.jobPrize.admin01_service.service;

import java.util.List;

import com.jobPrize.admin01_service.dto.ChatRequestDto;
import com.jobPrize.admin01_service.dto.ChatResponseDto;
import com.jobPrize.entity.common.ChatRoom;
import com.jobPrize.entity.common.User;

public interface ChatService {
	void createMessage(ChatRequestDto chatRequestDto) throws Exception;
	void createChatRoom(User user1, User user2) throws Exception;
	void chatNotify(); // 채팅 알림 기능, 추후 구현.
    List<ChatRoom> readChatRoomList(Long id) throws Exception;
	List<ChatResponseDto> readMessagesList(Long roomId) throws Exception;
}