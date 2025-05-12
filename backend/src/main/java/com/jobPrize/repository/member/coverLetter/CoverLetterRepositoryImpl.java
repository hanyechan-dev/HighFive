package com.jobPrize.repository.member.coverLetter;

import java.util.Optional;

import com.jobPrize.entity.member.CoverLetter;
import com.jobPrize.entity.member.QCoverLetter;
import com.jobPrize.entity.member.QCoverLetterContent;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CoverLetterRepositoryImpl implements CoverLetterRepositoryCustom {
	private final JPAQueryFactory queryFactory;
	
	@Override
	public Optional<CoverLetter> findWithCoverLetterContentsByCoverLetterId(Long id) {
		QCoverLetter coverLetter = QCoverLetter.coverLetter;
		QCoverLetterContent coverLetterContent = QCoverLetterContent.coverLetterContent;
		
		CoverLetter result = queryFactory.selectFrom(coverLetter)
				.leftJoin(coverLetter.coverLetterContents, coverLetterContent).fetchJoin()
				.where(coverLetter.id.eq(id))
				.distinct()
				.fetchOne();
		
		return Optional.ofNullable(result);
	}

}
