package com.jobPrize.controller.admin01;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobPrize.dto.common.payment.PaymentRequestDto;
import com.jobPrize.dto.common.subscription.SubscriptionResponseDto;
import com.jobPrize.entity.common.UserType;
import com.jobPrize.service.common.subscription.SubscriptionService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/subscription")
@RequiredArgsConstructor
public class SubscriptionController {
	private final SubscriptionService subscriptionService;
	
	// 구독자 조회
	@GetMapping
	public ResponseEntity<List<SubscriptionResponseDto>> getSubscription(UserType userType, UserType targetUserType){
		List<SubscriptionResponseDto> subs = subscriptionService.readSubscriberListByUserType(userType, targetUserType);
		return ResponseEntity.ok(subs);
	}
	
	// 구독자 생성
	@PostMapping("/create")
	public ResponseEntity<Void> createSubscription(@RequestBody Long id, UserType userType, PaymentRequestDto paymentRequestDto){
		subscriptionService.createSubscription(id, userType, paymentRequestDto);
		return ResponseEntity.ok().build();
	}

}