package com.jobPrize.controller.admin;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobPrize.dto.admin.service.CountByDayDto;
import com.jobPrize.dto.admin.service.CountByMonthDto;
import com.jobPrize.dto.admin.service.SubsCountDto;
import com.jobPrize.dto.admin.service.UserCountDto;
import com.jobPrize.enumerate.UserType;
import com.jobPrize.service.admin.admin.AdminService;
import com.jobPrize.util.SecurityUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/service")
@RequiredArgsConstructor
public class AdminServiceController {
    private final AdminService adminService;
    
    // 지정된 기간 내 발생한 회원가입, 회원탈퇴 수를 사용자 유형에 따라 날짜(day)별로 조회
    @GetMapping("/reg-and-cancel/day")
    public ResponseEntity<List<CountByDayDto>> getSignUpAndWithdrawalByDay(@RequestParam("days") int days, @RequestParam("userType") UserType userType){
    	UserType adminCheck = SecurityUtil.getUserType();
    	
    	if(adminCheck == UserType.관리자) {
    		List<CountByDayDto> dtoList = adminService.countAllSignUpAndWithdrawalByDay(days, userType);
    		return ResponseEntity.status(HttpStatus.OK).body(dtoList);
    	} else { return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.emptyList()); }
    }
    
    // 지정된 기간 내 발생한 회원가입, 회원탈퇴 수를 사용자 유형에 따라 월(month)별로 조회
    @GetMapping("/reg-and-cancel/month")
    public ResponseEntity<List<CountByMonthDto>> getSignUpAndWithdrawalByMonth(@RequestParam("months") int months, @RequestParam("userType") UserType userType){
    	UserType adminCheck = SecurityUtil.getUserType();
    	
    	if(adminCheck == UserType.관리자) {
    		List<CountByMonthDto> dtoList = adminService.countAllSignUpAndWithdrawalByMonth(months, userType);
    		return ResponseEntity.status(HttpStatus.OK).body(dtoList);
    	} else { return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.emptyList()); }
    }
    
    // 각 사용자 유형에 따른 구독자, 비구독자를 List로 취합하여 반환
    @GetMapping("/count-subs")
    public ResponseEntity<List<SubsCountDto>> getAllSubsByUserType(){
    	UserType userType = SecurityUtil.getUserType();
    	
    	if(userType == UserType.관리자) {
    		List<SubsCountDto> dto = adminService.countAllSubsWithUserType();
    		return ResponseEntity.status(HttpStatus.OK).body(dto);
    	} else {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.emptyList());
    	}
    }
    
    // 각 UserType에 대한 총 회원을 List로 취합하여 반환
    @GetMapping("/count-users")
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
