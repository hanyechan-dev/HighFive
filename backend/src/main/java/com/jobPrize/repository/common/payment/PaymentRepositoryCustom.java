package com.jobPrize.repository.common.payment;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jobPrize.dto.admin.service.PaymentCountDto;
import com.jobPrize.entity.common.Payment;
import com.jobPrize.enumerate.UserType;

public interface PaymentRepositoryCustom {
	Page<Payment> findAllByUserId(Long id, Pageable pageable);
	
	// 시간 남았을 때 추후 구현
	List<Payment> findAllByUserTypeAndPeriod(LocalDateTime start, LocalDateTime end, UserType userType);
	
	// 지정된 기간 내 발생한 매출을 사용자 유형에 따라 단위기간별로 조회
	List<PaymentCountDto> countPaymentByPeriod(int period, UserType userType);
}
