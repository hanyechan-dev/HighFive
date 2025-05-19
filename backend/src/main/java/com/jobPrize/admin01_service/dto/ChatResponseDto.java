package com.jobPrize.admin01_service.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatResponseDto {
    private Long chatRoomId;
    private String senderName;
    private String content;
    private LocalDateTime createdAt;
}