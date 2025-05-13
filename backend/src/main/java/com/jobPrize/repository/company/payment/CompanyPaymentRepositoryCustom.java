package com.jobPrize.repository.company.payment;

import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Page;

import com.jobPrize.entity.common.Payment;

public interface CompanyPaymentRepositoryCustom {
	Page<Payment> findAllByCompanyId(Long id, Pageable pageable);
}
