package com.jobPrize.repository.consultant.consultantConsulting;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.jobPrize.entity.consultant.ConsultantConsulting;
import com.jobPrize.entity.consultant.QConsultantConsulting;
import com.jobPrize.entity.consultant.QConsultantConsultingContent;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConsultantConsultingRepositoryImpl implements ConsultantConsultingRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public Optional<ConsultantConsulting> findWithConsultantConsultingContentsByConsultantConsultingId(Long id) {

		QConsultantConsulting consultantConsulting = QConsultantConsulting.consultantConsulting;
		QConsultantConsultingContent consultantConsultingContent = QConsultantConsultingContent.consultantConsultingContent;

//		QAiConsulting aiConsulting = QAiConsulting.aiConsulting;
//		QAiConsultingContent aiConsultingContent = QAiConsultingContent.aiConsultingContent;
//
//		QRequest request = QRequest.request;
//		QRequestDocument requestDocument = QRequestDocument.requestDocument;
//		
//		QUser user = QUser.user;
//	    QMember member = QMember.member;

		ConsultantConsulting result = queryFactory
				.selectFrom(consultantConsulting)
				.leftJoin(consultantConsulting.consultantConsultingContents, consultantConsultingContent).fetchJoin()
//				.leftJoin(consultantConsulting.aiConsulting, aiConsulting).fetchJoin()
//				.leftJoin(aiConsulting.aiConsultingContents, aiConsultingContent).fetchJoin()
//				.leftJoin(aiConsulting.request, request).fetchJoin()
//				.leftJoin(request.member, member).fetchJoin() // 추가
//		        .leftJoin(member.user, user).fetchJoin()      // 추가
//				.leftJoin(request.requestDocument, requestDocument).fetchJoin()
				.where(consultantConsulting.id.eq(id))
				.distinct()
				.fetchOne();

		return Optional.ofNullable(result);
	}
	
	@Override
	public Page<ConsultantConsulting> findWithAllConsultantConsultingByConsultantId(Long id, Pageable pageable) {
		QConsultantConsulting consultantConsulting = QConsultantConsulting.consultantConsulting;

		List<ConsultantConsulting> results = queryFactory
				.selectFrom(consultantConsulting)
				.where(consultantConsulting.consultant.id.eq(id))
				.orderBy(consultantConsulting.id.desc())
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();
		
		return new PageImpl<>(results, pageable, countConsultantConsultingByConsultantId(id));

	}
	
	public long countConsultantConsultingByConsultantId(Long id) {
		QConsultantConsulting consultantConsulting = QConsultantConsulting.consultantConsulting;

	    return Optional.ofNullable(
	    	queryFactory
	        .select(consultantConsulting.count())
	        .from(consultantConsulting)
	        .where(consultantConsulting.consultant.id.eq(id))
	        .fetchOne())
	    	.orElse(0L);
	}

}