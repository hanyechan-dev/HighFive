package com.jobPrize.admin01_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.admin01_service.dto.ChatRequestDto;
import com.jobPrize.admin01_service.dto.ChatResponseDto;
import com.jobPrize.entity.common.ChatContent;
import com.jobPrize.entity.common.ChatRoom;
import com.jobPrize.entity.common.User;
import com.jobPrize.repository.common.UserRepository;
import com.jobPrize.repository.common.chatContent.ChatContentRepository;
import com.jobPrize.repository.common.chatRoom.ChatRoomRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatContentRepository chatContentRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;

    // 메세지 저장
	@Override
	public void createMessage(ChatRequestDto chatRequestDto) throws Exception {
		ChatRoom chatRoom = chatRoomRepository.findById(chatRequestDto.getChatRoomId())
				.orElseThrow(() -> new EntityNotFoundException("ChatRoom not found"));
		User user = userRepository.findById(chatRequestDto.getId())
				.orElseThrow(() -> new EntityNotFoundException("User not found"));
		
		ChatContent newMessage = ChatContent.builder()
				.user(user)
				.chatRoom(chatRoom)
				.content(chatRequestDto.getContent())
				.build();
		
		chatContentRepository.save(newMessage);
    }
	
	// 채팅방 생성
	@Override
	public void createChatRoom(User user1, User user2) throws Exception {
		ChatRoom chatRoom = ChatRoom.builder()
				.user1(user1)
				.user2(user2)
				.build();
		
		chatRoomRepository.save(chatRoom);
	}
	
	// 채팅 알림 기능, 추후 구현.
	@Override
	public void chatNotify() {
		
	}
    
    // 채팅방 리스트 조회
    @Transactional(readOnly = true)
    @Override
    public List<ChatRoom> readChatRoomList(Long id) throws Exception {
        List<ChatRoom> chatRooms = chatRoomRepository.findAllByUserId(id);
        return chatRooms;
    }
	
    // 채팅 메세지 조회
    @Transactional(readOnly = true)
	@Override
	public List<ChatResponseDto> readMessagesList(Long roomId) throws Exception {
        ChatRoom chatRoom = chatRoomRepository.findWithChatContentsByChatRoomId(roomId)
            .orElseThrow(() -> new EntityNotFoundException("ChatRoom not found with id: " + roomId));

        return chatRoom.getChatContents().stream()
            .map(chatContent -> ChatResponseDto.builder()
                .chatRoomId(roomId)
                .id(chatContent.getUser().getId())
                .name(chatContent.getUser().getName())
                .content(chatContent.getContent())
                .createdAt(chatContent.getCreatedTime())
                .build()
            )
            .collect(Collectors.toList());
	}

}