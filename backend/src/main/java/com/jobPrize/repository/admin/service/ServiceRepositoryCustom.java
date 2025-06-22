package com.jobPrize.repository.admin.service;

import java.util.List;

import com.jobPrize.dto.admin.service.CountByDayDto;
import com.jobPrize.dto.admin.service.CountByMonthDto;
import com.jobPrize.dto.admin.service.SubsCountDto;
import com.jobPrize.dto.admin.service.UserCountDto;
import com.jobPrize.enumerate.UserType;

public interface ServiceRepositoryCustom {
	// 지정된 기간 내 발생한 회원가입, 회원탈퇴 수를 사용자 유형에 따라 날짜(day)별로 조회
	List<CountByDayDto> countSignUpAndWithDrawalByDay(int days, UserType userType);
	
	// 지정된 기간 내 발생한 회원가입, 회원탈퇴 수를 사용자 유형에 따라 월(month)별로 조회
	List<CountByMonthDto> countSignUpAndWithDrawalByMonth(int months, UserType userType);
	
	// 사용자 유형에 따른 구독자 및 비구독자 조회
	SubsCountDto countSubsByUserType(UserType userType);
	
	// 각 UserType에 대한 총 회원을 List로 취합하여 반환
	UserCountDto countUsersByUserType(UserType userType);
}
