package com.jobPrize.admin01_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRequestDto {
    @NotBlank(message = "알림을 보내는 사용자 ID는 비어 있을 수 없습니다.")
    private Long userId;

    @NotNull
    private boolean isRead; // 알림 확인 여부
}
