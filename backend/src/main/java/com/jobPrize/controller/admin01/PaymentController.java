package com.jobPrize.controller.admin01;


import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobPrize.dto.common.payment.PaymentRequestDto;
import com.jobPrize.dto.common.payment.PaymentResponseDto;
import com.jobPrize.entity.common.UserType;
import com.jobPrize.service.common.payment.PaymentService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {
	private final PaymentService paymentService;
	
	// 결제
	@PostMapping("/create")
	public ResponseEntity<Void> createPayment(@RequestBody Long id, UserType userType, PaymentRequestDto paymentRequestDto){
		paymentService.createPayment(id, userType, paymentRequestDto);
		return ResponseEntity.ok().build();
	}
	
	// ID별 결제 내역 리스트 조회
	@GetMapping("/listById")
	public ResponseEntity<List<PaymentResponseDto>> getPaymentListById(@RequestParam Long id, Pageable pageable) {
		List<PaymentResponseDto> paymentListById = paymentService.readPaymentListById(id, pageable);
		return ResponseEntity.ok(paymentListById);
	}
	
	// 전체 결제 내역 리스트 조회
	@GetMapping("/list")
	public ResponseEntity<List<PaymentResponseDto>> getPaymentList() {
		List<PaymentResponseDto> paymentList = paymentService.readPaymentList();
		return ResponseEntity.ok(paymentList);
	}
	
}
