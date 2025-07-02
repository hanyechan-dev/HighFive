package com.jobPrize.repository.memToCon.request;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.jobPrize.entity.consultant.QAiConsulting;
import com.jobPrize.entity.consultant.QConsultantConsulting;
import com.jobPrize.entity.memToCon.QRequest;
import com.jobPrize.entity.memToCon.Request;
import com.jobPrize.enumerate.ConsultingType;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RequestRepositoryImpl implements RequestRepositoryCustom{
	
	private final JPAQueryFactory queryFactory;

	@Override
	public Page<Request> findAllByMemberIdAndType(Long id, ConsultingType type, Pageable pageable) {
		QRequest request = QRequest.request;
		
		List<Request> results = queryFactory
				.selectFrom(request)
				.join(request.member).fetchJoin()
				.where(
					request.member.id.eq(id),
					request.consultingType.eq(type)
					)
				.orderBy(request.createdDate.desc())
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
			    .fetch();
		
		
		return new PageImpl<Request>(results,pageable, countRequestsByMemberId(id, type));
	}

	@Override
	public Optional<Request> findWithAiConsultingByRequestId(Long id) {
		QRequest request = QRequest.request;
		QAiConsulting aiConsulting = QAiConsulting.aiConsulting;


		Request result = queryFactory
				.selectFrom(request)
				.join(request.aiConsulting,aiConsulting).fetchJoin()
				.join(aiConsulting.aiConsultingContents).fetchJoin()
				.where(request.id.eq(id))
				.distinct()
				.fetchOne();
				
				
		return Optional.ofNullable(result);
	}
	
	@Override
	public Optional<Request> findWithConsultantConsultingByRequestId(Long id) {
		
		QRequest request = QRequest.request;
		QAiConsulting aiConsulting = QAiConsulting.aiConsulting;
		QConsultantConsulting consultantConsulting = QConsultantConsulting.consultantConsulting;
		
		Request result = queryFactory
				.selectFrom(request)
				.join(request.aiConsulting,aiConsulting).fetchJoin()
				.join(aiConsulting.consultantConsulting, consultantConsulting).fetchJoin()
				.join(consultantConsulting.consultantConsultingContents) 
				.where(request.id.eq(id))
				.distinct()
				.fetchOne();
		
		return Optional.ofNullable(result);
	}


	@Override
	public Optional<Long> findMemberIdByRequestId(Long id) {
		QRequest request = QRequest.request;
		
		Long result = queryFactory
				.select(request.member.id)
				.from(request)
				.where(request.id.eq(id))
				.fetchOne();
				
		return Optional.ofNullable(result);
	}
	
	private long countRequestsByMemberId(Long id, ConsultingType type) {
		QRequest request = QRequest.request;

	    return Optional.ofNullable(
	    	queryFactory
	        .select(request.count())
	        .from(request)
	        .where(request.member.id.eq(id))
	        .where(request.consultingType.eq(type))
	        .fetchOne())
	    	.orElse(0L);
	}


}
