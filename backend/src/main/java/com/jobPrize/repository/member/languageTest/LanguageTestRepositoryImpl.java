package com.jobPrize.repository.member.languageTest;

import java.util.List;

import com.jobPrize.entity.member.LanguageTest;
import com.jobPrize.entity.member.QLanguageTest;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LanguageTestRepositoryImpl implements LanguageTestRepositoryCostom{
	
	private final JPAQueryFactory queryFactory;
	@Override
	public List<LanguageTest> findAllByMemberId(Long id) {
		QLanguageTest languageTest = QLanguageTest.languageTest;
		
		List<LanguageTest> results = queryFactory
				.selectFrom(languageTest)
				.join(languageTest.member).fetchJoin()
				.where(languageTest.member.id.eq(id))
				.orderBy(languageTest.acquisitionDate.desc())
				.fetch();
		
		return results;
	}
}
