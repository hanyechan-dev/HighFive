package com.jobPrize.repository.consultant.aiConsulting;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.jobPrize.entity.common.QUser;
import com.jobPrize.entity.consultant.AiConsulting;
import com.jobPrize.entity.consultant.QAiConsulting;
import com.jobPrize.entity.memToCon.QRequest;
import com.jobPrize.entity.member.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class AiConsultingRepositoryImpl implements AiConsultingRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public Optional<AiConsulting> findWithAllRequestByAiConsultingId(Long id) {  //상세 모달
		QAiConsulting aiConsulting = QAiConsulting.aiConsulting;
	    QRequest request = QRequest.request;
	    QUser user = QUser.user;
	    QMember member = QMember.member;

		
		AiConsulting result = queryFactory
				.selectFrom(aiConsulting)
		        .leftJoin(aiConsulting.aiConsultingContents).fetchJoin()
		        .join(aiConsulting.request, request).fetchJoin()
		        .leftJoin(request.member, member).fetchJoin() // member 추가
		        .join(member.user, user).fetchJoin() // user 추가
		        .where(aiConsulting.id.eq(id))
		        .distinct()
		        .fetchOne();

		return Optional.ofNullable(result);
	}
	
	@Override
	public Page<AiConsulting> findAllByCondition(Pageable pageable) {  //컨설팅 요청 페이지
		QAiConsulting aiConsulting = QAiConsulting.aiConsulting;
		
		List<AiConsulting> results = queryFactory
				.selectFrom(aiConsulting)
				.leftJoin(aiConsulting.consultantConsulting).fetchJoin()
				.where(
					aiConsulting.requestedDate.isNotNull(),
					aiConsulting.consultantConsulting.isNull()
					)
				.orderBy(aiConsulting.requestedDate.desc())
				.offset(pageable.getOffset()) //몇 번째부터 시작할지
				.limit(pageable.getPageSize()) //몇 개 가져올지
				.distinct()

				.fetch();
		
		return new PageImpl<>(results, pageable, countAiConsultingsByCondition());

	}
	
	public long countAiConsultingsByCondition() {
		
		QAiConsulting aiConsulting = QAiConsulting.aiConsulting;
		
		return Optional.ofNullable(
			queryFactory
	        .select(aiConsulting.count())
	        .from(aiConsulting)
	        .leftJoin(aiConsulting.consultantConsulting)
	        .where(
					aiConsulting.requestedDate.isNotNull(),
					aiConsulting.consultantConsulting.isNull()
					)
	        .fetchOne())
		    .orElse(0L); // null이면 0으로 대체
	}

}