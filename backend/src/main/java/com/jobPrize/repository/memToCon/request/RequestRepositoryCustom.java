package com.jobPrize.repository.memToCon.request;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jobPrize.entity.memToCon.Request;
import com.jobPrize.enumerate.ConsultingType;

public interface RequestRepositoryCustom {
	public Page<Request> findAllByMemberIdAndType(Long id, ConsultingType type, Pageable pageable);
	Optional<Request> findWithAiConsultingByRequestId(Long id);
	Optional<Long> findMemberIdByRequestId(Long id);
}
