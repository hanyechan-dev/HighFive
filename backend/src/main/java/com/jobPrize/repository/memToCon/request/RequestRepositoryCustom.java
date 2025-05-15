package com.jobPrize.repository.memToCon.request;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jobPrize.entity.memToCon.Request;

public interface RequestRepositoryCustom {
	Page<Request> findAllByMemberId(Long id, Pageable pageable);
	Optional<Request> findWithAiConsultingByRequestId(Long id);
}
