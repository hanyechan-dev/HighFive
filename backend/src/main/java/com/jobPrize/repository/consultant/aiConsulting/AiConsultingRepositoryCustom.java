package com.jobPrize.repository.consultant.aiConsulting;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jobPrize.entity.consultant.AiConsulting;

public interface AiConsultingRepositoryCustom {
	Optional<AiConsulting> findWithAllRequestByAiConsultingId(Long id);
	Page<AiConsulting> findAllByCondition(Pageable pageable);

}
