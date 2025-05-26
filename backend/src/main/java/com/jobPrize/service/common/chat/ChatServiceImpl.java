package com.jobPrize.service.common.chat;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.customException.CustomEntityNotFoundException;
import com.jobPrize.dto.common.chat.ChatRequestDto;
import com.jobPrize.dto.common.chat.ChatResponseDto;
import com.jobPrize.entity.common.ChatContent;
import com.jobPrize.entity.common.ChatRoom;
import com.jobPrize.entity.common.User;
import com.jobPrize.entity.common.UserType;
import com.jobPrize.repository.common.chatContent.ChatContentRepository;
import com.jobPrize.repository.common.chatRoom.ChatRoomRepository;
import com.jobPrize.repository.common.user.UserRepository;

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
	public void createMessage(Long id, ChatRequestDto chatRequestDto) {
		ChatRoom chatRoom = chatRoomRepository.findById(chatRequestDto.getChatRoomId())
				.orElseThrow(() -> new CustomEntityNotFoundException("채팅방"));
		User user = userRepository.findById(id)
				.orElseThrow(() -> new CustomEntityNotFoundException("유저"));
		
		ChatContent newMessage = ChatContent.builder()
				.user(user)
				.chatRoom(chatRoom)
				.content(chatRequestDto.getContent())
				.build();
		
		chatContentRepository.save(newMessage);
    }
	
	// 채팅방 생성
	@Override
	public void createChatRoom(Long id, Long targetId) {
		User user1 = userRepository.findById(id)
				.orElseThrow(() -> new CustomEntityNotFoundException("유저"));
		User user2 = userRepository.findById(targetId)
				.orElseThrow(() -> new CustomEntityNotFoundException("유저"));
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
    public List<ChatResponseDto> readChatRoomList(Long id) {
        List<ChatRoom> chatRooms = chatRoomRepository.findAllByUserId(id);
        
        return chatRooms.stream()
        		.map(chatRoom -> {
        	User otherUser = chatRoom.getUser1().getId().equals(id)
        			? chatRoom.getUser2()
        			: chatRoom.getUser1();
        	
        	String name = otherUser.getType().equals(UserType.기업회원)
        			? otherUser.getCompany().getCompanyName()
        			: otherUser.getName();
        	
        	return ChatResponseDto.builder()
        			.name(name)
        			.build();
        })
        .collect(Collectors.toList());
    }
	
    // 채팅 메세지 조회
    @Transactional(readOnly = true)
	@Override
	public List<ChatResponseDto> readMessagesList(Long roomId) {
        ChatRoom chatRoom = chatRoomRepository.findWithChatContentsByChatRoomId(roomId)
        		.orElseThrow(() -> new CustomEntityNotFoundException("채팅방"));

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