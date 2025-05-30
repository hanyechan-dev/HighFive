package com.jobPrize.controller.common;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobPrize.dto.common.chat.ChatResponseDto;
import com.jobPrize.dto.common.id.IdDto;
import com.jobPrize.service.common.chat.ChatService;
import com.jobPrize.util.SecurityUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/chats")
@RequiredArgsConstructor
public class ChatController {
	private final ChatService chatService;
	
	// 채팅방 리스트 조회
	@GetMapping
	public ResponseEntity<List<ChatResponseDto>> getChatRooms(){
		Long id = SecurityUtil.getId();
		List<ChatResponseDto> chatRooms = chatService.readChatRoomList(id);
		return ResponseEntity.status(HttpStatus.OK).body(chatRooms);
	}
	
	// 채팅 메세지 조회
	@PostMapping("/detail")
	public ResponseEntity<List<ChatResponseDto>> getMessages(@RequestBody @Valid IdDto idDto){
		Long id = SecurityUtil.getId();
		Boolean check = chatService.checkUser(id, idDto.getId());
		
		if(check == true) {
			List<ChatResponseDto> chatMessages = chatService.readMessagesList(idDto.getId());
			return ResponseEntity.status(HttpStatus.OK).body(chatMessages);
		} else { return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.emptyList()); }
	}
	
	// 채팅방 생성
	@PostMapping
	public ResponseEntity<Void> createChatRoom(@RequestBody @Valid IdDto idDto) {
		Long id = SecurityUtil.getId();
		chatService.createChatRoom(id, idDto.getId());
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
}