package com.jobPrize.repository.common.service;

import java.util.List;

import com.jobPrize.dto.admin.service.CountByDayDto;
import com.jobPrize.dto.admin.service.CountByMonthDto;
import com.jobPrize.dto.admin.service.SubsCountDto;
import com.jobPrize.dto.admin.service.UserCountDto;
import com.jobPrize.enumerate.UserType;

public interface ServiceRepositoryCustom {
	// 지정된 기간 내 발생한 회원가입, 회원탈퇴 수를 사용자 유형에 따라 날짜(day)별로 조회
	List<CountByDayDto> countSignUpAndWithDrawByDay(int days, UserType userType);
	
	// 지정된 기간 내 발생한 회원가입, 회원탈퇴 수를 사용자 유형에 따라 월(month)별로 조회
	List<CountByMonthDto> countSignUpAndWithDrawByMonth(int months, UserType userType);
	
//	// 지정된 기간 내 발생한 회원가입 수를 날짜별로 사용자 유형에 따라 조회
//	List<CountByDateDto> findSignUpByPeriod(LocalDate date, UserType userType);
//	
//	// 지정된 기간 내 발생한 회원탈퇴 수를 날짜별로 사용자 유형에 따라 조회
//	List<CountByDateDto> findWithdrawByPeriod(LocalDate date, UserType userType);
	
	// 사용자 유형에 따른 구독자 및 비구독자 조회
	SubsCountDto countSubsByUserType(UserType userType);
	
	// 사용자 유형에 따른 총 회원 수 조회
	UserCountDto countUsersByUserType(UserType userType);
}
