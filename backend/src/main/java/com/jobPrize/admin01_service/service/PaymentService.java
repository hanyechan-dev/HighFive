package com.jobPrize.admin01_service.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.jobPrize.admin01_service.dto.PaymentRequestDto;
import com.jobPrize.admin01_service.dto.PaymentResponseDto;
import com.jobPrize.entity.common.UserType;

public interface PaymentService {
	
	// 결제 기능
	void createPayment(Long id, UserType userType, PaymentRequestDto paymentRequestDto);
	
	// ID별 결제 내역 리스트 조회
	List<PaymentResponseDto> readPaymentListById(Long id, Pageable pageable);
	
	// 전체 결제 내역 리스트 조회
	List<PaymentResponseDto> readPaymentList();
}
