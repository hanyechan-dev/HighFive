package com.jobPrize.repository.consultant;

import java.util.Optional;

import com.jobPrize.entity.consultant.ConsultantConsulting;

public interface ConsultantConsultingRepositoryCustom {
		
	
	Optional<ConsultantConsulting> findWithAllRequestByConsultantConsultingId(Long id);

}
