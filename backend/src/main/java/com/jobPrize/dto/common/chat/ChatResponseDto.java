package com.jobPrize.dto.common.chat;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChatResponseDto {
    private Long chatRoomId;
    private Long id;
    private String name;
    private String content;
    private LocalDateTime createdAt;
}