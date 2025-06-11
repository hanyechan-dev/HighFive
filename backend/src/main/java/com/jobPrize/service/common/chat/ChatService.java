package com.jobPrize.service.common.chat;

import java.util.List;

import com.jobPrize.dto.common.chat.ChatRequestDto;
import com.jobPrize.dto.common.chat.ChatResponseDto;
import com.jobPrize.entity.common.ChatContent;

public interface ChatService {
	ChatResponseDto createMessage(ChatRequestDto chatRequestDto);
	Long createChatRoom(Long id, Long targetId);
	void chatNotify(); // 채팅 알림 기능, 추후 구현.
    List<ChatResponseDto> readChatRoomList(Long id);
	List<ChatResponseDto> readMessagesList(Long roomId);
	public Boolean checkUser(Long id, Long roomId);
}