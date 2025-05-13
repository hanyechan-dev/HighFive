package com.jobPrize.repository.company.payment;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import com.jobPrize.entity.common.Payment;
import com.jobPrize.entity.common.QPayment;
import com.jobPrize.entity.memToCom.QSimilarity;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CompanyPaymentRepositoryImpl implements CompanyPaymentRepositoryCustom {
	
	private final JPAQueryFactory queryFactory;
	
	@Override
	public 	Page<Payment> findAllByCompanyId(Long id, Pageable pageable){
		QPayment payment= QPayment.payment;
		
		List<Payment> results = queryFactory
				.selectFrom(payment)
				.where(payment.user.id.eq(id))
				.orderBy(payment.createdTime.desc())
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();
		
		return new PageImpl<Payment>(results, pageable, countPaymentByCompanyId(id));
	}
	public long countPaymentByCompanyId(Long id) {
		QPayment payment = QPayment.payment;

		return Optional.ofNullable( 
				queryFactory.select(payment.count())
				.from(payment)
				.where(payment.user.id.eq(id))
				.fetchOne()
				).orElse(0L);
	}
	
}


