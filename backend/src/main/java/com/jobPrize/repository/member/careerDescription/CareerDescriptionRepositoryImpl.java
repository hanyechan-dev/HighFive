package com.jobPrize.repository.member.careerDescription;

import java.util.List;
import java.util.Optional;

import com.jobPrize.entity.member.CareerDescription;
import com.jobPrize.entity.member.QCareerDescription;
import com.jobPrize.entity.member.QCareerDescriptionContent;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CareerDescriptionRepositoryImpl implements CareerDescriptionRepositoryCustom {
	private final JPAQueryFactory queryFactory;
	
	@Override
	public Optional<CareerDescription> findWithCareerDescriptionContentsByCareerDescriptionId(Long id) {
		QCareerDescription careerDescription = QCareerDescription.careerDescription;
		QCareerDescriptionContent careerDescriptionContent = QCareerDescriptionContent.careerDescriptionContent;
		
		CareerDescription result = queryFactory.selectFrom(careerDescription)
				.leftJoin(careerDescription.careerDescriptionContents, careerDescriptionContent).fetchJoin()
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
				.where(careerDescription.member.id.eq(id))
				.orderBy(careerDescription.createdDate.desc())
				.fetch();
		
		return results;
	}
}
