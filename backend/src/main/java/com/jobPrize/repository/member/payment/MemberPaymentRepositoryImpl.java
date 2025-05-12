package com.jobPrize.repository.member.payment;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.jobPrize.entity.common.Payment;
import com.jobPrize.entity.common.QPayment;
import com.jobPrize.entity.member.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberPaymentRepositoryImpl implements MemberPaymentRepositoryCustom{
	private final JPAQueryFactory queryFactory;
	@Override
	public Page<Payment> findAllByMemberId(Long id, Pageable pageable) {
		QPayment payment = QPayment.payment;
		QMember member = QMember.member;
		
		List<Payment> results = queryFactory
				.selectFrom(payment)
				.where(payment.user.id.eq(id))
				.orderBy(payment.createdTime.desc())
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
			    .fetch();
		
		
		return new PageImpl<Payment>(results,pageable, countPaymentsByMemberId(id));
	}
	
	public long countPaymentsByMemberId(Long id) {
		QPayment payment = QPayment.payment;

	    return queryFactory
	        .select(payment.count())
	        .from(payment)
	        .where(payment.user.id.eq(id))
	        .fetchOne();
	}

}
