package com.jobPrize.admin01_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobPrize.admin01_service.dto.ChatRequestDto;
import com.jobPrize.admin01_service.dto.ChatResponseDto;
import com.jobPrize.entity.common.ChatContent;
import com.jobPrize.entity.common.ChatRoom;
import com.jobPrize.jwt.TokenProvider;
import com.jobPrize.repository.common.chatRoom.ChatRoomRepositoryImpl;

import jakarta.persistence.EntityNotFoundException;

@Service

public class ChatServiceImpl implements ChatService {

    private final TokenProvider tokenProvider;
    private final ChatRoomRepository chatRoomRepository;

    @Autowired
    public ChatServiceImpl(TokenProvider tokenProvider, ChatRoomRepositoryImpl chatRoomRepositoryImpl){
        this.tokenProvider = tokenProvider;
        this.chatRoomRepository = chatRoomRepository;
    }

    @Override
    public List<ChatRoom> selectChatRooms(String token) throws Exception {
        
        // ID 추출
        Long userId = Long.parseLong(tokenProvider.getIdFromToken(token));

        List<ChatRoom> chatRooms = chatRoomRepositoryImpl.findAllByUserId(userId);
        return chatRooms;
    }
	
	@Override
	public List<ChatResponseDto> selectMessages(Long roomId) throws Exception {
        ChatRoom chatRoom = chatRoomRepository.findWithChatContentsByChatRoomId(roomId)
            .orElseThrow(() -> new EntityNotFoundException());

        return chatRoom.getChatContents().stream()
            .map(c -> new ChatContentDTO(
                c.getUser().getUsername(),  // User에 getUsername() 또는 getNickname() 등이 있다고 가정
                c.getContent(),
                c.getCreatedTime()
            ))
            .collect(Collectors.toList());
	}

	@Override
	public void insertMessage(ChatRequestDto chatRequestDto) throws Exception {

    }