package com.jobPrize.repository.common.payment;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jobPrize.entity.common.Payment;
import com.jobPrize.entity.common.UserType;

public interface PaymentRepositoryCustom {
	Page<Payment> findAllByUserId(Long id, Pageable pageable);
	
	// 시간 남았을 때 추후 구현
	List<Payment> findAllByUserTypeAndPeriod(LocalDateTime start, LocalDateTime end, UserType userType);
}
