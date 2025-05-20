package com.jobPrize.admin01_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatRequestDto {

    @NotBlank(message = "채팅방 ID는 비어 있을 수 없습니다.")
    private Long chatRoomId;

    @NotBlank(message = "발신자는 비어 있을 수 없습니다.")
    private Long senderId;
    
    @NotBlank(message = "채팅 내용은 비어 있을 수 없습니다.")
    @Size(max = 100, message = "채팅 내용은 최대 100자까지 입력 가능합니다.")
    private String content;
}