package com.jobPrize.admin01_service.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.jobPrize.admin01_service.dto.PaymentRequestDto;
import com.jobPrize.admin01_service.dto.PaymentResponseDto;

public interface PaymentService {
	
	// 결제 기능
	void createPayment(PaymentRequestDto paymentRequestDto) throws Exception;
	
	// 결제 내역 리스트 조회
	List<PaymentResponseDto> readPaymentList(Long id, Pageable pageable) throws Exception;
	
	// 결제 내용 조회
	PaymentResponseDto readPayment(Long paymentId) throws Exception;
}
