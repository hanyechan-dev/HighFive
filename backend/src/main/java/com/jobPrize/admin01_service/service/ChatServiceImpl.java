package com.jobPrize.admin01_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.admin01_service.dto.ChatRequestDto;
import com.jobPrize.admin01_service.dto.ChatResponseDto;
import com.jobPrize.entity.common.ChatContent;
import com.jobPrize.entity.common.ChatRoom;
import com.jobPrize.jwt.TokenProvider;
import com.jobPrize.repository.common.UserRepository;
import com.jobPrize.repository.common.chatContent.ChatContentRepository;
import com.jobPrize.repository.common.chatRoom.ChatRoomRepository;

import jakarta.persistence.EntityNotFoundException;

@Service

public class ChatServiceImpl implements ChatService {

    private final ChatContentRepository chatContentRepository;

    private final TokenProvider tokenProvider;
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;

    @Autowired
    public ChatServiceImpl(TokenProvider tokenProvider, ChatRoomRepository chatRoomRepository, UserRepository userRepository, ChatContentRepository chatContentRepository){
        this.tokenProvider = tokenProvider;
        this.chatRoomRepository = chatRoomRepository;
        this.userRepository = userRepository;
        this.chatContentRepository = chatContentRepository;
    }

    @Transactional
	@Override
	public void createMessage(ChatRequestDto chatRequestDto) throws Exception {
		ChatRoom chatRoom = chatRoomRepository.findById(chatRequestDto.getChatRoomId())
				.orElseThrow(() -> new EntityNotFoundException("ChatRoom not found"));
		
		ChatContent newMessage = ChatContent.builder()
				.user(userRepository.findById(chatRequestDto.getSenderId())
						.orElseThrow(() -> new EntityNotFoundException("User not found")))
				.chatRoom(chatRoom)
				.content(chatRequestDto.getContent())
				.build();
		
		chatContentRepository.save(newMessage);
    }
    
    @Transactional(readOnly = true)
    @Override
    public List<ChatRoom> readChatRoomsList(String token) throws Exception {
        
        Long userId = Long.parseLong(tokenProvider.getIdFromToken(token));

        List<ChatRoom> chatRooms = chatRoomRepository.findAllByUserId(userId);
        return chatRooms;
    }
	
    @Transactional(readOnly = true)
	@Override
	public List<ChatResponseDto> readMessagesList(Long roomId) throws Exception {
        ChatRoom chatRoom = chatRoomRepository.findWithChatContentsByChatRoomId(roomId)
            .orElseThrow(() -> new EntityNotFoundException("ChatRoom not found with id: " + roomId));

        return chatRoom.getChatContents().stream()
            .map(chatContent -> ChatResponseDto.builder()
                .chatRoomId(roomId)
                .senderId(chatContent.getUser().getId())
                .senderName(chatContent.getUser().getName())
                .content(chatContent.getContent())
                .createdAt(chatContent.getCreatedTime())
                .build()
            )
            .collect(Collectors.toList());
	}

}