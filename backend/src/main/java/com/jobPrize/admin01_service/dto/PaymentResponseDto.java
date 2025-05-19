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
public class PaymentResponseDto {
    private Long paymentAmount;
    private String status;  // 결제 상태
    private LocalDateTime createdTime;  // 결제 시각
}
