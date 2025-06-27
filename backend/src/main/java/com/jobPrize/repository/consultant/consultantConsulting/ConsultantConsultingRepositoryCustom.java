package com.jobPrize.repository.consultant.consultantConsulting;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jobPrize.dto.admin.service.ConsultingCountDto;
import com.jobPrize.entity.consultant.ConsultantConsulting;

public interface ConsultantConsultingRepositoryCustom {
		

	Optional<ConsultantConsulting> findWithConsultantConsultingContentsByConsultantConsultingId(Long id);
	Page<ConsultantConsulting> findWithAllConsultantConsultingByConsultantId(Long id, Pageable pageable);
	Optional<Long> findConsultantIdByConsultantConsultingId(Long id);
	Optional<Long> findMemberIdByConsultantConsultingId(Long id);
	
	// 지정된 기간 내 작업 완료된 컨설팅 수를 단위기간별로 조회
	List<ConsultingCountDto> countConsultingByPeriod(int period);
}
