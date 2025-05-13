package com.jobPrize.repository.company.payment;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobPrize.entity.common.Payment;

public interface CompanyPaymentRepository extends JpaRepository<Payment, Long>, CompanyPaymentRepositoryCustom{

}