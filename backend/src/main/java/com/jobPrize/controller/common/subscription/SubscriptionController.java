package com.jobPrize.controller.common.subscription;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobPrize.dto.common.payment.PaymentRequestDto;
import com.jobPrize.entity.common.UserType;
import com.jobPrize.service.common.subscription.SubscriptionService;
import com.jobPrize.util.SecurityUtil;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;
    
    
    // 구독자 생성
    @PostMapping
    public ResponseEntity<Void> createSubscription(@RequestBody PaymentRequestDto paymentRequestDto){
    	Long id = SecurityUtil.getId();
    	UserType userType = SecurityUtil.getUserType();
    	
        subscriptionService.createSubscription(id, userType, paymentRequestDto);
        return ResponseEntity.ok().build();
    }

}