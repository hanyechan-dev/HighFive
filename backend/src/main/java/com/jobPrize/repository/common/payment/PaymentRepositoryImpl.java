package com.jobPrize.repository.common.payment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.jobPrize.entity.common.Payment;
import com.jobPrize.entity.common.QPayment;
import com.jobPrize.entity.common.QUser;
import com.jobPrize.entity.common.UserType;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PaymentRepositoryImpl implements PaymentRepositoryCustom{
	private final JPAQueryFactory queryFactory;
	@Override
	public Page<Payment> findAllByMemberId(Long id, Pageable pageable) {
		QPayment payment = QPayment.payment;
		
		List<Payment> results = queryFactory
				.selectFrom(payment)
				.join(payment.user).fetchJoin()
				.where(payment.user.id.eq(id))
				.orderBy(payment.createdTime.desc())
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
			    .fetch();
		
		
		return new PageImpl<Payment>(results,pageable, countPaymentsByMemberId(id));
	}
	
	@Override
	public List<Payment> findAllByUserTypeAndPeriod(LocalDateTime start, LocalDateTime end, UserType userType) { // 지정된 기간 및 사용자 유형에 따른 매출 조회
		QPayment payment = QPayment.payment;
		
		List<Payment> results = queryFactory
				.selectFrom(payment)
				.join(payment.user).fetchJoin()
				.where(payment.createdTime.between(start, end),
						payment.user.type.eq(userType))
				.orderBy(payment.createdTime.asc())
				.fetch();
		
		return results;
	}
	
	public long countPaymentsByMemberId(Long id) {
		QPayment payment = QPayment.payment;

		return Optional.ofNullable( 
				queryFactory.select(payment.count())
				.from(payment)
				.where(payment.user.id.eq(id))
				.fetchOne()
				).orElse(0L);
	}
}

