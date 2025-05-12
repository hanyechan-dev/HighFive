package com.jobPrize.repository.member.payment;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobPrize.entity.common.Payment;

public interface MemberPaymentRepository extends JpaRepository<Payment, Long>, MemberPaymentRepositoryCustom{

}
