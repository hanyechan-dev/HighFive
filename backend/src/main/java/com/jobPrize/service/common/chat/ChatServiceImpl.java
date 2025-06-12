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
import com.jobPrize.enumerate.UserType;
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

	private final static String ENTITY_NAME = "채팅방";
	
    // 메세지 저장
	@Override
	public ChatResponseDto createMessage(ChatRequestDto chatRequestDto) {
		Long id = chatRequestDto.getSenderId();
		Long chatRoomId = chatRequestDto.getChatRoomId();
		String content = chatRequestDto.getContent();
		
		ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId)
				.orElseThrow(() -> new CustomEntityNotFoundException("ENTITY_NAME"));
		User user = userRepository.findByIdAndDeletedDateIsNull(id)
				.orElseThrow(() -> new CustomEntityNotFoundException("유저"));
		
		ChatContent newMessage = ChatContent.builder()
				.user(user)
				.chatRoom(chatRoom)
				.content(content)
				.build();
		
		ChatContent chatContent = chatContentRepository.save(newMessage);
		
		ChatResponseDto chatResponseDto = ChatResponseDto.builder()
				.chatRoomId(chatRequestDto.getChatRoomId())
				.senderId(chatRequestDto.getSenderId())
				.name(chatContent.getUser().getName())
				.content(chatRequestDto.getContent())
				.createdAt(chatContent.getCreatedTime())
				.build();
		
		return chatResponseDto;
    }
	
	// 채팅방 생성
	@Override
	public Long createChatRoom(Long id, Long targetId) {
		
		// 채팅방 중복 생성 방지
		ChatRoom testChatRoom = chatRoomRepository.findChatRoomWithTwoUsers(id, targetId);
		if( testChatRoom != null ) {
			Long chatRoomId = testChatRoom.getId();
			return chatRoomId;
		}
		
		User user1 = userRepository.findByIdAndDeletedDateIsNull(id)
				.orElseThrow(() -> new CustomEntityNotFoundException("유저"));
		User user2 = userRepository.findByIdAndDeletedDateIsNull(targetId)
				.orElseThrow(() -> new CustomEntityNotFoundException("유저"));
		ChatRoom chatRoom = ChatRoom.builder()
				.user1(user1)
				.user2(user2)
				.build();
		
		chatRoomRepository.save(chatRoom);
		
		// ChatRoomId 반환
		ChatRoom newChatRoom = chatRoomRepository.findChatRoomWithTwoUsers(id, targetId);
		Long chatRoomId = newChatRoom.getId();
		
		return chatRoomId;
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
        			.chatRoomId(chatRoom.getId())
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
        		.orElseThrow(() -> new CustomEntityNotFoundException(ENTITY_NAME));

        return chatRoom.getChatContents().stream()
            .map(chatContent -> ChatResponseDto.builder()
                .chatRoomId(roomId)
                .senderId(chatContent.getUser().getId())
                .name(chatContent.getUser().getName())
                .content(chatContent.getContent())
                .createdAt(chatContent.getCreatedTime())
                .build()
            )
            .collect(Collectors.toList());
	}
    
    @Override
    // 채팅방 소속 여부 확인
    public Boolean checkUser(Long id, Long roomId) {
    	Boolean check = chatRoomRepository.checkMemberInChatRoom(id, roomId);
    	return check != null;
    }

}