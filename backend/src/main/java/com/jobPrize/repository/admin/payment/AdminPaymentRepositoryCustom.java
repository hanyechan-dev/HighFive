package com.jobPrize.repository.admin.payment;

import java.time.LocalDateTime;
import java.util.List;

import com.jobPrize.entity.common.Payment;
import com.jobPrize.entity.common.UserType;

public interface AdminPaymentRepositoryCustom {
	
	List<Payment> findAllByUserTypeAndPeriod(LocalDateTime start, LocalDateTime end, UserType userType); // 지정된 기간 및 사용자 유형에 따른 매출 통계
		
}
