package com.jobPrize.service.common.payment;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jobPrize.dto.common.payment.PaymentRequestDto;
import com.jobPrize.dto.common.payment.PaymentResponseDto;
import com.jobPrize.enumerate.UserType;

public interface PaymentService {
	
	// 결제 기능
	void createPayment(Long id, UserType userType, PaymentRequestDto paymentRequestDto);
	
	// ID별 결제 내역 리스트 조회
	Page<PaymentResponseDto> readPaymentPageById(Long id, Pageable pageable);
	
	// 전체 결제 내역 리스트 조회
	List<PaymentResponseDto> readPaymentList();
}
