package com.jobPrize.dto.common.notification;

import com.jobPrize.enumerate.NotificationType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateDto {
    private Long receiverId;
    private NotificationType notificationType;
}
