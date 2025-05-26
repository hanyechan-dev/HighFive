package com.jobPrize.admin01_service.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PaymentResponseDto {
	private Long paymentId;	// 주문 번호
	private Long id;	// 사용자 ID
    private int paymentAmount;	// 결제 금액
    private String content; // 결제 내역
    private LocalDateTime createdTime;  // 결제 시각
    private String method;
}
