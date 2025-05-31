package com.jobPrize.repository.member.coverLetter;

import java.util.List;
import java.util.Optional;

import com.jobPrize.entity.member.CoverLetter;
import com.jobPrize.entity.member.QCoverLetter;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CoverLetterRepositoryImpl implements CoverLetterRepositoryCustom {
	private final JPAQueryFactory queryFactory;
	
	@Override
	public Optional<CoverLetter> findWithCoverLetterContentsByCoverLetterId(Long id) {
		QCoverLetter coverLetter = QCoverLetter.coverLetter;
		
		CoverLetter result = queryFactory.selectFrom(coverLetter)
				.leftJoin(coverLetter.coverLetterContents).fetchJoin()
				.where(coverLetter.id.eq(id))
				.distinct()
				.fetchOne();
		
		return Optional.ofNullable(result);
	}

	@Override
	public List<CoverLetter> findAllByMemberId(Long id) {
		QCoverLetter coverLetter = QCoverLetter.coverLetter;
		
		List<CoverLetter> results = queryFactory
				.selectFrom(coverLetter)
				.join(coverLetter.member).fetchJoin()
				.where(coverLetter.member.id.eq(id))
				.orderBy(coverLetter.createdDate.desc())
				.fetch();
		
		return results;
	}

	@Override
	public Optional<Long> findMemberIdByCoverLetterId(Long id) {
		QCoverLetter coverLetter = QCoverLetter.coverLetter;
		
		Long result = queryFactory.select(coverLetter.member.id)
				.from(coverLetter)
				.where(coverLetter.id.eq(id))
				.fetchOne();
		
		return Optional.ofNullable(result);
	}



}
