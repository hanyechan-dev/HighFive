package com.jobPrize.admin01_service.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.jobPrize.admin01_service.dto.PaymentRequestDto;
import com.jobPrize.admin01_service.dto.PaymentResponseDto;

public interface PaymentService {
	
	// 결제 기능
	void createPayment(PaymentRequestDto paymentRequestDto) throws Exception;
	
	// 결제 내역 조회(주문명세서)
	// PaymentResponseDto 반환.
	List<PaymentResponseDto> readPayment(Long id, Pageable pageable) throws Exception;
}
