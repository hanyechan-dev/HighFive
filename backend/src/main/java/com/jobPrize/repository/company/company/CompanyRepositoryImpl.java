package com.jobPrize.repository.company.company;

import java.util.Optional;

import com.jobPrize.entity.common.QUser;
import com.jobPrize.entity.company.Company;
import com.jobPrize.entity.company.QCompany;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CompanyRepositoryImpl implements CompanyRepositoryCustom {
	
    private final JPAQueryFactory queryFactory;

	@Override
	public Optional<Company> findByIdAndDeletedDateIsNull(Long id) {
        QCompany company = QCompany.company;
        QUser user = QUser.user;

        Company result = queryFactory
            .selectFrom(company)
            .join(company.user, user).fetchJoin()
            .where(company.id.eq(id),
                   user.deletedDate.isNull())
            .fetchOne();

        return Optional.ofNullable(result);
	}

}
