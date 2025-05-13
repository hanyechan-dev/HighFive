package com.jobPrize.repository.admin.payment;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobPrize.entity.common.Payment;

public interface AdminPaymentRepository extends JpaRepository<Payment, Long>, AdminPaymentRepositoryCustom {
}
