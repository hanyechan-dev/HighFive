package com.jobPrize.repository.member.payment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jobPrize.entity.common.Payment;

public interface MemberPaymentRepositoryCustom {
	Page<Payment> findAllByMemberId(Long id, Pageable pageable);
}
