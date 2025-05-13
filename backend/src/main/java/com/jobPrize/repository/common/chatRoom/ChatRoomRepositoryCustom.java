package com.jobPrize.repository.common.chatRoom;

import java.util.List;
import java.util.Optional;

import com.jobPrize.entity.common.ChatRoom;

public interface ChatRoomRepositoryCustom {
	List<ChatRoom> findAll();	// 채팅방 리스트 조회(가장 최근 메시지의 시간을 기준으로 내림차순 정렬)
	
	Optional<ChatRoom> findWithChatContentsByChatRoomId(Long id);	// 채팅방 번호를 통해 채팅 내용과 연결
}
