package com.jobPrize.controller.admin01;

import java.util.Collections;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobPrize.dto.common.chat.ChatResponseDto;
import com.jobPrize.service.common.chat.ChatService;
import com.jobPrize.util.SecurityUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {
	private final ChatService chatService;
	
	// 채팅방 리스트 조회
	@GetMapping
	public ResponseEntity<List<ChatResponseDto>> getChatRooms(){
		Long id = SecurityUtil.getId();
		List<ChatResponseDto> chatRooms = chatService.readChatRoomList(id);
		return ResponseEntity.ok(chatRooms);
	}
	
	// 채팅 메세지 조회
	@GetMapping("/{roomId}")
	public ResponseEntity<List<ChatResponseDto>> getMessages(@RequestParam Long roomId){
		Long id = SecurityUtil.getId();
		Boolean check = chatService.checkUser(id, roomId);
		
		if(check == true) {
			List<ChatResponseDto> chatMessages = chatService.readMessagesList(roomId);
			return ResponseEntity.ok(chatMessages);
		} else { return ResponseEntity.badRequest().body(Collections.emptyList()); }
	}
	
	// 채팅방 생성
	@PostMapping("/room")
	public ResponseEntity<Void> createChatRoom(@RequestBody Long targetId) {
		Long id = SecurityUtil.getId();
		chatService.createChatRoom(id, targetId);
		return ResponseEntity.ok().build();
	}
	
}