package com.jobPrize.admin01_service.service;

import com.jobPrize.admin01_service.dto.PaymentRequestDto;
import com.jobPrize.admin01_service.dto.PaymentResponseDto;

public interface PaymentService {
	// 1. (추후 구현) 결제 API 연동
	
	// 2. PaymentRequestDto의 각 필드값을 추출하여 엔티티에 반영 후 DB 저장.
	void createPayment(PaymentRequestDto paymentRequestDto);
	
	// 3. 2번 과정 완료 시, PaymentRequestDto의 일부 필드값을 추출하여 SubscriptionRequestDto에 할당.
	//    SubscriptionServiceImpl.createSubscription(SubscriptionRequestDto)를 통해 구독 중으로 상태 변경
	//	  1번부터 3번까지 Transactional이어야 함.
	void updateSubsStatus(PaymentRequestDto paymentRequestDto);
	
	// 3. 결제 내역 조회(주문명세서)
	//    PaymentResponseDto 반환.
	PaymentResponseDto paymentResponseDto(Long id);
}
