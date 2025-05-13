package com.jobPrize.repository.company.proposal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jobPrize.entity.memToCom.Proposal;

public interface CompanyProposalRepositoryCustom {
	Page<Proposal> findAllByCompanyId(Long id, Pageable pageable);
}
