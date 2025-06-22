package com.jobPrize.repository.common.payment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.jobPrize.dto.admin.service.PaymentCountDto;
import com.jobPrize.entity.common.Payment;
import com.jobPrize.entity.common.QPayment;
import com.jobPrize.enumerate.UserType;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.DateTemplate;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PaymentRepositoryImpl implements PaymentRepositoryCustom{
	private final JPAQueryFactory queryFactory;
	@Override
	public Page<Payment> findAllByUserId(Long id, Pageable pageable) {
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
	
	// (시간 남았을 때 추후 구현)
	// 지정된 기간 및 사용자 유형에 따른 매출 조회
	@Override
	public List<Payment> findAllByUserTypeAndPeriod(LocalDateTime start, LocalDateTime end, UserType userType) {
		QPayment payment = QPayment.payment;
		
		List<Payment> results = queryFactory
				.selectFrom(payment)
				.leftJoin(payment.user).fetchJoin()
				.where(payment.createdTime.between(start, end),
						payment.user.type.eq(userType))
				.orderBy(payment.createdTime.asc())
				.fetch();
		
		return results;
	}
	
	// 지정된 기간 내 발생한 매출을 사용자 유형에 따라 단위기간별로 조회
	public List<PaymentCountDto> countPaymentByPeriod(int period, UserType userType){
		QPayment payment = QPayment.payment;
		
		LocalDateTime endDate = LocalDateTime.now();
		LocalDateTime startDate;
		DateTemplate<java.sql.Date> dateTemplate = Expressions.dateTemplate(
				java.sql.Date.class, "DATE({0})", payment.createdTime); 
		
		if(period == 7 || period == 30) {
			startDate = endDate.minusDays(period);
		} else if(period == 6 || period == 12) {
			startDate = endDate.minusMonths(period);
		} else {
			startDate = endDate;
		}
		
		List<PaymentCountDto> results = queryFactory
				.select(
					Projections.constructor(PaymentCountDto.class,
						dateTemplate,
						payment.paymentAmount.count()
					)
				)
				.from(payment)
				.where(
						payment.user.type.eq(userType)
						.and(payment.createdTime.between(startDate, endDate))
				)
				.groupBy(dateTemplate)
				.orderBy(dateTemplate.asc())
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

