package com.jobPrize.repository.memToCon.request;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.jobPrize.entity.consultant.CommonEnum;
import com.jobPrize.entity.consultant.QAiConsulting;
import com.jobPrize.entity.memToCon.QRequest;
import com.jobPrize.entity.memToCon.Request;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RequestRepositoryImpl implements RequestRepositoryCustom{
	
	private final JPAQueryFactory queryFactory;

	@Override
	public Page<Request> findAllByMemberIdAndType(Long id, CommonEnum.ConsultingType type, Pageable pageable) {
		QRequest request = QRequest.request;
		
		List<Request> results = queryFactory
				.selectFrom(request)
				.join(request.member).fetchJoin()
				.where(
					request.member.id.eq(id),
					request.type.eq(type)
					)
				.orderBy(request.createdDate.desc())
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
			    .fetch();
		
		
		return new PageImpl<Request>(results,pageable, countRequestsByMemberId(id));
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
