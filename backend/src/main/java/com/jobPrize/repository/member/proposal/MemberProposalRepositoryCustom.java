package com.jobPrize.repository.member.proposal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jobPrize.entity.memToCom.Proposal;

public interface MemberProposalRepositoryCustom {
	Page<Proposal> findProposalsByMemberId(Long id, Pageable pageable);
}
