package com.jobPrize.repository.member.careerDescription;

import java.util.List;
import java.util.Optional;

import com.jobPrize.entity.member.CareerDescription;
import com.jobPrize.entity.member.QCareerDescription;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CareerDescriptionRepositoryImpl implements CareerDescriptionRepositoryCustom {
	private final JPAQueryFactory queryFactory;
	
	@Override
	public Optional<CareerDescription> findWithCareerDescriptionContentsByCareerDescriptionId(Long id) {
		QCareerDescription careerDescription = QCareerDescription.careerDescription;
		
		CareerDescription result = queryFactory.selectFrom(careerDescription)
				.leftJoin(careerDescription.careerDescriptionContents).fetchJoin()
				.where(careerDescription.id.eq(id))
				.distinct()
				.fetchOne();
		
		return Optional.ofNullable(result);
	}
	@Override
	public List<CareerDescription> findAllByMemberId(Long id) {
		QCareerDescription careerDescription = QCareerDescription.careerDescription;
		
		List<CareerDescription> results = queryFactory
				.selectFrom(careerDescription)
				.join(careerDescription.member).fetchJoin()
				.where(careerDescription.member.id.eq(id))
				.orderBy(careerDescription.createdDate.desc())
				.fetch();
		
		return results;
	}
	

}
