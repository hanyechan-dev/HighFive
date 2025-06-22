package com.jobPrize.repository.consultant.consultantConsulting;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.jobPrize.dto.admin.service.ConsultingCountDto;
import com.jobPrize.entity.consultant.ConsultantConsulting;
import com.jobPrize.entity.consultant.QConsultantConsulting;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConsultantConsultingRepositoryImpl implements ConsultantConsultingRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public Optional<ConsultantConsulting> findWithConsultantConsultingContentsByConsultantConsultingId(Long id) { //상세 모달

		QConsultantConsulting consultantConsulting = QConsultantConsulting.consultantConsulting;

		ConsultantConsulting result = queryFactory
				.selectFrom(consultantConsulting)
				.leftJoin(consultantConsulting.consultantConsultingContents).fetchJoin()
				.where(consultantConsulting.id.eq(id))
				.distinct()
				.fetchOne();

		return Optional.ofNullable(result);
	}
	
	@Override
	public Page<ConsultantConsulting> findWithAllConsultantConsultingByConsultantId(Long id, Pageable pageable) { //컨설팅 현황 관리 페이지
		QConsultantConsulting consultantConsulting = QConsultantConsulting.consultantConsulting;

		List<ConsultantConsulting> results = queryFactory
				.selectFrom(consultantConsulting)
				.join(consultantConsulting.consultant).fetchJoin()
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

	@Override
	public Optional<Long> findConsultantIdByConsultantConsultingId(Long id) {
		QConsultantConsulting consultantConsulting = QConsultantConsulting.consultantConsulting;

		return Optional.ofNullable(
			queryFactory
			.select(consultantConsulting.consultant.id)
			.from(consultantConsulting)
			.where(consultantConsulting.id.eq(id))
			.fetchOne());
	}

	@Override
	public Optional<Long> findMemberIdByConsultantConsultingId(Long id) {
		QConsultantConsulting consultantConsulting = QConsultantConsulting.consultantConsulting;

		return Optional.ofNullable(
			queryFactory
			.select(consultantConsulting.aiConsulting.request.member.id)
			.from(consultantConsulting)
			.where(consultantConsulting.id.eq(id))
			.fetchOne());
	}
	
	// 지정된 기간 내 작업 완료된 컨설팅 수를 단위기간별로 조회
	@Override
	public List<ConsultingCountDto> countConsultingByPeriod(int period){
		QConsultantConsulting consultantConsulting = QConsultantConsulting.consultantConsulting;
		LocalDate endDate = LocalDate.now();
		LocalDate startDate;
		
		if(period == 7 || period == 30) {
			startDate = endDate.minusDays(period);
		} else if(period == 6 || period == 12) {
			startDate = endDate.minusMonths(period);
		} else {
			startDate = LocalDate.now();
		}
		
		List<ConsultingCountDto> dtoList = queryFactory
				.select(
						Projections.constructor(ConsultingCountDto.class,
								consultantConsulting.completedDate,
								consultantConsulting.count()
						)
				)
				.from(consultantConsulting)
				.where(
						consultantConsulting.completedDate.between(startDate, endDate)
				)
				.groupBy(consultantConsulting.completedDate)
				.orderBy(consultantConsulting.completedDate.asc())
				.fetch();
		
		return dtoList;
	}
}