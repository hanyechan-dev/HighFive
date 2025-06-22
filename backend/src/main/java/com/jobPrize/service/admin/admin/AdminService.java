package com.jobPrize.service.admin.admin;

import java.util.List;

import com.jobPrize.dto.admin.service.ConsultingCountDto;
import com.jobPrize.dto.admin.service.CountByDayDto;
import com.jobPrize.dto.admin.service.CountByMonthDto;
import com.jobPrize.dto.admin.service.PaymentCountDto;
import com.jobPrize.dto.admin.service.SubsCountDto;
import com.jobPrize.dto.admin.service.UserCountDto;
import com.jobPrize.enumerate.UserType;

public interface AdminService {
	public void createAdmin(Long id, UserType userType);
	
	// 지정된 기간 내 발생한 회원가입, 회원탈퇴 수를 사용자 유형에 따라 날짜(day)별로 조회
	List<CountByDayDto> countAllSignUpAndWithdrawalByDay(int days, UserType userType);
	
	// 지정된 기간 내 발생한 회원가입, 회원탈퇴 수를 사용자 유형에 따라 월(month)별로 조회
	public List<CountByMonthDto> countAllSignUpAndWithdrawalByMonth(int months, UserType userType);
	
	// 사용자 유형에 따른 구독자, 비구독자 수를 취합하여 반환
	List<SubsCountDto> countAllSubsWithUserType();
	
	// 각 UserType에 대한 총 회원을 List로 취합하여 반환
	List<UserCountDto> countAllUsersWithUserType();
	
	// 지정된 기간 내 발생한 매출을 사용자 유형에 따라 단위기간별로 조회
	List<PaymentCountDto> countPaymentByPeriod(int period, UserType userType);
	
	// 지정된 기간 내 작업 완료된 컨설팅 수를 단위기간별로 조회
	List<ConsultingCountDto> countConsultingByPeriod(int period);
}
