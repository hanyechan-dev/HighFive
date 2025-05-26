package com.jobPrize.admin01_service.service;

import java.util.List;

import com.jobPrize.admin01_service.dto.ChatRequestDto;
import com.jobPrize.admin01_service.dto.ChatResponseDto;
import com.jobPrize.entity.common.ChatRoom;
import com.jobPrize.entity.common.User;

public interface ChatService {
	void createMessage(ChatRequestDto chatRequestDto);
	void createChatRoom(Long id, Long targetId);
	void chatNotify(); // 채팅 알림 기능, 추후 구현.
    List<ChatResponseDto> readChatRoomList(Long id);
	List<ChatResponseDto> readMessagesList(Long roomId);
}