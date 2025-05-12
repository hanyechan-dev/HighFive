package com.jobPrize.repository.company.company;

import java.util.Optional;

import com.jobPrize.entity.company.Company;
import com.jobPrize.entity.company.QCompany;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CompanyRepositoryImpl implements CompanyRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public  Optional<Company> findByCompanyId(Long id) {
		QCompany company = QCompany.company;
		
	Company result = queryFactory	
				.selectFrom(company)
				.where(company.user.id.eq(id)) // 회원 ID로 회사 조회
				.fetchOne();
				
		return Optional.ofNullable(result);
	}

}
