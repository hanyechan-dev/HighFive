package com.jobPrize.repository.member.request;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jobPrize.entity.memToCon.Request;

public interface MemberRequestRepositoryCustom {
	Page<Request> findAllByMemberId(Long id, Pageable pageable);
	Optional<Request> findWithAllConsultingByRequestId(Long id);
}
