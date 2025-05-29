package com.jobPrize.controller.admin01;


import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobPrize.dto.common.payment.PaymentResponseDto;
import com.jobPrize.entity.common.UserType;
import com.jobPrize.service.common.payment.PaymentService;
import com.jobPrize.util.SecurityUtil;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/admin/payments")
@RequiredArgsConstructor
public class AdminPaymentController {

    private final PaymentService paymentService;
    
    // 전체 결제 내역 리스트 조회
    @GetMapping
    public ResponseEntity<List<PaymentResponseDto>> getPaymentList() {
    	
    	UserType userType = SecurityUtil.getUserType();
    	
    	if(userType == UserType.관리자) {
		        List<PaymentResponseDto> paymentList = paymentService.readPaymentList();
		        return ResponseEntity.ok(paymentList);
    	} else { return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.emptyList()); }
    }
}