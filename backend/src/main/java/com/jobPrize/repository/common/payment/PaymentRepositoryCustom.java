package com.jobPrize.repository.common.payment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jobPrize.entity.common.Payment;

public interface PaymentRepositoryCustom {
	Page<Payment> findAllByMemberId(Long id, Pageable pageable);
}
