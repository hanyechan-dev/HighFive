package com.jobPrize.controller.admin;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobPrize.dto.admin.service.SubsCountDto;
import com.jobPrize.dto.admin.service.UserCountDto;
import com.jobPrize.dto.common.subscription.SubscriptionResponseDto;
import com.jobPrize.enumerate.UserType;
import com.jobPrize.service.admin.admin.AdminService;
import com.jobPrize.service.common.subscription.SubscriptionService;
import com.jobPrize.util.SecurityUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/service")
@RequiredArgsConstructor
public class AdminServiceController {
    private final SubscriptionService subscriptionService;
    private final AdminService adminService;
    
    // 구독자 조회
    @GetMapping
    public ResponseEntity<List<SubscriptionResponseDto>> getSubscription(@RequestParam UserType targetUserType){
    	UserType userType = SecurityUtil.getUserType();
    	
    	if(userType == UserType.관리자) {
            List<SubscriptionResponseDto> subs = subscriptionService.readSubscriberListByUserType(userType, targetUserType);
            return ResponseEntity.status(HttpStatus.OK).body(subs);
    	} else { return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.emptyList()); }
    }
    
    // 각 사용자 유형에 따른 구독자, 비구독자를 List로 취합하여 반환
    @GetMapping("/count/subs")
    public ResponseEntity<List<SubsCountDto>> getAllSubsByUserType(){
    	UserType userType = SecurityUtil.getUserType();
    	
    	if(userType == UserType.관리자) {
    		List<SubsCountDto> dto = adminService.countAllSubsWithUserType();
    		return ResponseEntity.status(HttpStatus.OK).body(dto);
    	} else {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.emptyList());
    	}
    }
    
    // 각 사용자 유형의 총 회원을 List로 취합하여 반환
    @GetMapping("/count/users")
    public ResponseEntity<List<UserCountDto>> getAllUsersWithUserType(){
    	UserType userType = SecurityUtil.getUserType();
    	
    	if(userType == UserType.관리자) {
    		List<UserCountDto> dto = adminService.countAllUsersWithUserType();
    		return ResponseEntity.status(HttpStatus.OK).body(dto);
    	} else {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.emptyList());
    	}
    }
    
}
