package com.jobPrize.dto.common.chat;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
public class ChatResponseDto {
    private Long chatRoomId;
    private Long contentId;
    private Long senderId;
    private Long receiverId;
    private String name;
    private String content;
    private LocalDateTime createdAt;
}