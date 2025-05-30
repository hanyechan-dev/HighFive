package com.jobPrize.controller.admin;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobPrize.dto.common.subscription.SubscriptionResponseDto;
import com.jobPrize.enumerate.UserType;
import com.jobPrize.service.common.subscription.SubscriptionService;
import com.jobPrize.util.SecurityUtil;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/admin/subscriptions")
@RequiredArgsConstructor
public class AdminSubscriptionController {
    private final SubscriptionService subscriptionService;
    
    // 구독자 조회
    @GetMapping
    public ResponseEntity<List<SubscriptionResponseDto>> getSubscription(@RequestParam UserType targetUserType){
    	UserType userType = SecurityUtil.getUserType();
    	
    	if(userType == UserType.관리자) {
            List<SubscriptionResponseDto> subs = subscriptionService.readSubscriberListByUserType(userType, targetUserType);
            return ResponseEntity.status(HttpStatus.OK).body(subs);
    	} else { return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.emptyList()); }
    }
    

}