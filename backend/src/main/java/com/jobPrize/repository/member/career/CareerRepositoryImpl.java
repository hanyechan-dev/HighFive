package com.jobPrize.repository.member.career;

import java.util.List;

import com.jobPrize.entity.member.Career;
import com.jobPrize.entity.member.QCareer;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CareerRepositoryImpl implements CareerRepositoryCostom{
	
	private final JPAQueryFactory queryFactory;
	@Override
	public List<Career> findAllByMemberId(Long id) {
		QCareer career = QCareer.career;
		
		List<Career> results = queryFactory
				.selectFrom(career)
				.join(career.member).fetchJoin()
				.where(career.member.id.eq(id))
				.orderBy(career.startDate.desc())
				.fetch();
		
		return results;
	}
}
