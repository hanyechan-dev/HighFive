package com.jobPrize.repository.consultant.consultantConsulting;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jobPrize.entity.consultant.ConsultantConsulting;

public interface ConsultantConsultingRepositoryCustom {
		

	Optional<ConsultantConsulting> findWithAllRequestByConsultantConsultingId(Long id);
	Page<ConsultantConsulting> findWithAllConsultantConsultingByConsultantId(Long id, Pageable pageable);

}
