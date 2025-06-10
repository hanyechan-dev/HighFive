package com.jobPrize.dto.common.chat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ChatRequestDto {
	
	@NotBlank(message = "발신자 ID는 비어 있을 수 없습니다.")
	private Long senderId;
	
	@NotBlank(message = "수신자 ID는 비어 있을 수 없습니다.")
	private Long targetId;

    @NotBlank(message = "채팅방 ID는 비어 있을 수 없습니다.")
    private Long chatRoomId;
    
    @NotBlank(message = "채팅 내용은 비어 있을 수 없습니다.")
    @Size(max = 100, message = "채팅 내용은 최대 100자까지 입력 가능합니다.")
    private String content;
}