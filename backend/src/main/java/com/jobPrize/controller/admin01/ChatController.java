package com.jobPrize.controller.admin01;

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

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {
	private final ChatService chatService;
	
	// 채팅방 리스트 조회
	@GetMapping
	public ResponseEntity<List<ChatResponseDto>> getChatRooms(@RequestParam Long id){
		// 쿼리스트링 변환으로 인한 불법적 채팅방 조회 방지 코드 필요함
		
		List<ChatResponseDto> chatRooms = chatService.readChatRoomList(id);
		return ResponseEntity.ok(chatRooms);
	}
	
	// 채팅 메세지 조회
	@GetMapping("/{roomId}")
	public ResponseEntity<List<ChatResponseDto>> getMessages(@RequestParam Long roomId){
		List<ChatResponseDto> chatMessages = chatService.readMessagesList(roomId);
		return ResponseEntity.ok(chatMessages);
	}
	
	// 채팅방 생성
	@PostMapping("/room")
	public ResponseEntity<Void> createChatRoom(@RequestBody Long id, Long targetId) {
		chatService.createChatRoom(id, targetId);
		return ResponseEntity.ok().build();
	}
	
}