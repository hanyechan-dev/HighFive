package com.jobPrize.repository.member.certification;

import java.util.List;
import java.util.Optional;

import com.jobPrize.entity.member.Certification;
import com.jobPrize.entity.member.QCertification;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CertificationRepositoryImpl implements CertificationRepositoryCustom{
	
	private final JPAQueryFactory queryFactory;
	@Override
	public List<Certification> findAllByMemberId(Long id) {
		QCertification certification = QCertification.certification;
		
		List<Certification> results = queryFactory
				.selectFrom(certification)
				.join(certification.member).fetchJoin()
				.where(certification.member.id.eq(id))
				.orderBy(certification.acquisitionDate.desc())
				.fetch();
		
		return results;
	}

	@Override
	public Optional<Long> findMemberIdByCertificationId(Long id) {
		QCertification certification = QCertification.certification;

		Long result = queryFactory
			.select(certification.member.id)
			.from(certification)
			.where(certification.id.eq(id))
			.fetchOne();

		return Optional.ofNullable(result);
	}
}
