package com.jobPrize.admin01_service.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 우선적으로 뭘 정해야 하냐면...
// 알림을 눌렀을 때 link 기능을 추가할 것인가..
// 아니면 서버로부터 온 알림을 확인하면 서버에게 사용자가 확인한 것을 알리고
// notification 기능을 그저 사용자에게 알리기만 하는 용도로 사용할 것인가.
// 기능을 풍요롭게 하는 것은 좋으나, 우선은 후자의 방향으로 개발하고 추후 기능을 구현해도
// 괜찮을 듯.

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationResponseDto {
    private String userId;
    private String notificationId;
    private String content; // 알림 내용
    private LocalDateTime createdTime;
}
