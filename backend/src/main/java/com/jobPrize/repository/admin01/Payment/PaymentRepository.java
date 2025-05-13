package com.jobPrize.repository.admin01.Payment;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobPrize.entity.common.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>, PaymentRepositoryCustom {
}
