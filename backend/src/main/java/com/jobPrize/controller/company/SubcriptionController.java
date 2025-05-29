package com.jobPrize.controller.company;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobPrize.dto.common.subscription.SubscriptionResponseDto;
import com.jobPrize.service.common.subscription.SubscriptionService;
import com.jobPrize.util.SecurityUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("companies/subscriptions")
@RequiredArgsConstructor
public class SubcriptionController {

	private final SubscriptionService subscriptionService;

	@GetMapping
	public ResponseEntity<SubscriptionResponseDto>  getMySubscription() { 
		
		Long id = SecurityUtil.getId();

		SubscriptionResponseDto subscriptionResponseDto = subscriptionService.readMySubscription(id);

		return ResponseEntity.status(HttpStatus.OK).body(subscriptionResponseDto);
	}
}
