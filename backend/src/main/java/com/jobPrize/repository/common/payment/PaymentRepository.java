package com.jobPrize.repository.common.payment;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobPrize.entity.common.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>, PaymentRepositoryCustom{

}
