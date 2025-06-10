package com.jobPrize.repository.common.chatRoom;

import java.util.List;
import java.util.Optional;

import com.jobPrize.entity.common.ChatRoom;

public interface ChatRoomRepositoryCustom {
	List<ChatRoom> findAllByUserId(Long id);	// 채팅방 리스트 조회(가장 최근 메시지의 시간을 기준으로 내림차순 정렬)
	
	Optional<ChatRoom> findWithChatContentsByChatRoomId(Long id);	// 채팅방 번호를 통해 채팅 내용과 연결
	
	Boolean checkMemberInChatRoom(Long id, Long roomId);	// 채팅방 소속 여부 확인
	
	ChatRoom findChatRoomWithTwoUsers(Long id, Long targetId);
}
