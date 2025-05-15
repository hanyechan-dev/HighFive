package com.jobPrize.repository.consultant.aiConsulting;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.jobPrize.entity.common.QUser;
import com.jobPrize.entity.consultant.AiConsulting;
import com.jobPrize.entity.consultant.QAiConsulting;
import com.jobPrize.entity.consultant.QAiConsultingContent;
import com.jobPrize.entity.consultant.QConsultantConsulting;
import com.jobPrize.entity.memToCon.QRequest;
import com.jobPrize.entity.memToCon.QRequestDocument;
import com.jobPrize.entity.member.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class AiConsultingRepositoryImpl implements AiConsultingRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public Optional<AiConsulting> findWithAllRequestByAiConsultingId(Long id) {
		QAiConsulting aiConsulting = QAiConsulting.aiConsulting;
	    QAiConsultingContent aiConsultingContent = QAiConsultingContent.aiConsultingContent;
	    QRequest request = QRequest.request;
	    QRequestDocument requestDocument = QRequestDocument.requestDocument;
	    QUser user = QUser.user;
	    QMember member = QMember.member;

		
		AiConsulting result = queryFactory
				.selectFrom(aiConsulting)
		        .leftJoin(aiConsulting.aiConsultingContents, aiConsultingContent).fetchJoin()
		        .leftJoin(aiConsulting.request, request).fetchJoin()
		        .leftJoin(request.member, member).fetchJoin() // member 추가
		        .leftJoin(member.user, user).fetchJoin() // user 추가
		        .leftJoin(request.requestDocument, requestDocument).fetchJoin()
		        .where(aiConsulting.id.eq(id))
		        .distinct()
		        .fetchOne();

		return Optional.ofNullable(result);
	}
	
	@Override
	public Page<AiConsulting> findAllByCondition(Pageable pageable) {
		QAiConsulting aiConsulting = QAiConsulting.aiConsulting;
		QConsultantConsulting consultantConsulting = QConsultantConsulting.consultantConsulting;
		
		List<AiConsulting> results = queryFactory
				.selectFrom(aiConsulting)
				.leftJoin(aiConsulting.consultantConsulting, consultantConsulting).fetchJoin()
				.where(
					aiConsulting.isRequested.isTrue(),
					aiConsulting.consultantConsulting.isNull()
					)
				.orderBy(aiConsulting.requestedDate.desc())
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();
		
		return new PageImpl<>(results, pageable, countAiConsultingByCondition());

	}
	
	public long countAiConsultingByCondition() {
		
		QAiConsulting aiConsulting = QAiConsulting.aiConsulting;
		QConsultantConsulting consultantConsulting = QConsultantConsulting.consultantConsulting;
		
		return Optional.ofNullable(
			queryFactory
	        .select(aiConsulting.count())
	        .from(aiConsulting)
	        .leftJoin(aiConsulting.consultantConsulting, consultantConsulting).fetchJoin()
	        .where(
					aiConsulting.isRequested.eq(true),
					aiConsulting.consultantConsulting.isNull()
					)
	        .fetchOne())
		    .orElse(0L);
	}

}