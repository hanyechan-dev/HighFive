package com.jobPrize.controller.common;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobPrize.dto.common.chat.ChatRequestDto;
import com.jobPrize.dto.common.chat.ChatResponseDto;
import com.jobPrize.dto.common.id.IdDto;
import com.jobPrize.service.common.chat.ChatService;
import com.jobPrize.util.SecurityUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/chats")
@RequiredArgsConstructor
public class ChatController {
	private final ChatService chatService;
	private final SimpMessagingTemplate simpMessagingTemplate;
	
	// 채팅방 리스트 조회
	@GetMapping
	public ResponseEntity<List<ChatResponseDto>> getChatRooms(){
		Long id = SecurityUtil.getId();
		List<ChatResponseDto> chatRooms = chatService.readChatRoomList(id);
		return ResponseEntity.status(HttpStatus.OK).body(chatRooms);
	}
	
	// 채팅 메세지 조회
	@PostMapping("/detail")
	public ResponseEntity<List<ChatResponseDto>> getMessages(@RequestBody @Valid IdDto idDto){ // IdDto는 ChatRoomId
		Long id = SecurityUtil.getId();
		Boolean check = chatService.checkUser(id, idDto.getId()); // 채팅방 소속 여부 확인
		
		if(check == true) {
			List<ChatResponseDto> chatMessages = chatService.readMessagesList(idDto.getId());
			return ResponseEntity.status(HttpStatus.OK).body(chatMessages);
		} else { return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.emptyList()); }
	}
	
	// 채팅방 생성
	@PostMapping
	public ResponseEntity<Long> createChatRoom(@RequestBody @Valid IdDto idDto) {	// IdDto는 UserId
		Long id = SecurityUtil.getId();
		Long chatRoomId = chatService.createChatRoom(id, idDto.getId());
		return ResponseEntity.status(HttpStatus.OK).body(chatRoomId);
	}
	
	// 채팅 메세지 발송
	@MessageMapping("/chat/send")
	public void sendMessage(@RequestBody ChatRequestDto chatRequestDto) {
		String destination = "/topic/" + chatRequestDto.getChatRoomId();
		ChatResponseDto chatResponseDto = chatService.createMessage(chatRequestDto);
		System.out.println("메시지 발송 시도 -> 목적지: " + destination + ", 내용: " + chatResponseDto.getContent());
		simpMessagingTemplate.convertAndSend(destination, chatResponseDto);
	}	
}