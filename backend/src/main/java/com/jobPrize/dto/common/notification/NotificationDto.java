package com.jobPrize.dto.common.notification;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class NotificationDto {
    private Long notificationId;
    private Long id;
    private String message;
    private LocalDateTime createdTime;
}
