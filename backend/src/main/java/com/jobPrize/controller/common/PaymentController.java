package com.jobPrize.controller.common;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobPrize.dto.common.payment.KakaoReadyResponseDto;
import com.jobPrize.dto.common.payment.PaymentRequestDto;
import com.jobPrize.dto.common.payment.PaymentResponseDto;
import com.jobPrize.enumerate.UserType;
import com.jobPrize.service.common.payment.PaymentService;
import com.jobPrize.util.SecurityUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

	private final PaymentService paymentService;

	// 결제
	@PostMapping
	public ResponseEntity<KakaoReadyResponseDto> createPayment(@RequestBody PaymentRequestDto paymentRequestDto) {
		Long id = SecurityUtil.getId();
		UserType userType = SecurityUtil.getUserType();

		KakaoReadyResponseDto kakaoReadyResponseDto = paymentService.createPayment(id, userType, paymentRequestDto);
		return ResponseEntity.status(HttpStatus.OK).body(kakaoReadyResponseDto);
	}

	// ID별 결제 내역 리스트 조회
	@GetMapping
	public ResponseEntity<Page<PaymentResponseDto>> getPaymentList(Pageable pageable) {
		
		Long id = SecurityUtil.getId();
		
		Page<PaymentResponseDto> paymentListById = paymentService.readPaymentPageById(id, pageable);
		return ResponseEntity.status(HttpStatus.OK).body(paymentListById);

	}

}