package com.jobPrize.repository.admin01.Payment;

import java.time.LocalDateTime;
import java.util.List;

import com.jobPrize.entity.common.Payment;
import com.jobPrize.entity.common.QPayment;
import com.jobPrize.entity.common.QUser;
import com.jobPrize.entity.common.UserType;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PaymentRepositoryImpl implements PaymentRepositoryCustom {
	private final JPAQueryFactory queryFactory;

	@Override
	public List<Payment> findAllByUserTypeAndPeriod(LocalDateTime start, LocalDateTime end, UserType userType) { // 지정된 기간 및 사용자 유형에 따른 매출 조회
		QPayment payment = QPayment.payment;
		QUser user = QUser.user;
		
		List<Payment> results = queryFactory
				.selectFrom(payment)
				.join(payment.user, user).fetchJoin()
				.where(payment.createdTime.between(start, end),
						payment.user.type.eq(userType))
				.orderBy(payment.createdTime.asc())
				.fetch();
		
		return results;
	}
}