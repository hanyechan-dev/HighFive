package com.jobPrize.admin01_service.dto;

import java.time.LocalDateTime;

import com.jobPrize.entity.common.UserType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponseDto {
	private Long id;	// 사용자 ID
	private UserType userType;	// 사용자 구분
	private Long paymentId;	// 주문 번호
    private Long paymentAmount;	// 결제 금액
    private LocalDateTime createdTime;  // 결제 시각
}
