package com.jobPrize.repository.member.request;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.jobPrize.entity.consultant.QAiConsulting;
import com.jobPrize.entity.consultant.QAiConsultingContent;
import com.jobPrize.entity.consultant.QConsultantConsulting;
import com.jobPrize.entity.consultant.QConsultantConsultingContent;
import com.jobPrize.entity.memToCon.QRequest;
import com.jobPrize.entity.memToCon.QRequestDocument;
import com.jobPrize.entity.memToCon.Request;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberRequestRepositoryImpl implements MemberRequestRepositoryCustom{
	
	private final JPAQueryFactory queryFactory;

	@Override
	public Page<Request> findAllByMemberId(Long id, Pageable pageable) {
		QRequest request = QRequest.request;
		
		List<Request> results = queryFactory
				.selectFrom(request)
				.where(request.member.id.eq(id))
				.orderBy(request.createdDate.desc())
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
			    .fetch();
		
		
		return new PageImpl<Request>(results,pageable, countRequestsByMemberId(id));
	}

	@Override
	public Optional<Request> findWithAiConsultingByRequestId(Long id) {
		QRequest request = QRequest.request;
		QRequestDocument requestDocument = QRequestDocument.requestDocument;
		QAiConsulting aiConsulting = QAiConsulting.aiConsulting;
		QAiConsultingContent aiConsultingContent = QAiConsultingContent.aiConsultingContent;

		
		Request result = queryFactory
				.selectFrom(request)
				.leftJoin(request.requestDocument, requestDocument).fetchJoin()
				.leftJoin(request.aiConsulting, aiConsulting).fetchJoin()
				.leftJoin(aiConsulting.aiConsultingContents, aiConsultingContent).fetchJoin()
				.where(request.id.eq(id))
				.distinct()
				.fetchOne();
				
				
		return Optional.ofNullable(result);
	}
	
	public long countRequestsByMemberId(Long id) {
		QRequest request = QRequest.request;

	    return Optional.ofNullable(
	    	queryFactory
	        .select(request.count())
	        .from(request)
	        .where(request.member.id.eq(id))
	        .fetchOne())
	    	.orElse(0L);
	}

}
