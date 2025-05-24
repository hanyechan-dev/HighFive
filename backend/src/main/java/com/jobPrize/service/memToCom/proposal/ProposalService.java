package com.jobPrize.service.memToCom.proposal;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jobPrize.dto.memToCom.proposal.ProposalCreateDto;
import com.jobPrize.dto.memToCom.proposal.ProposalResponseDto;
import com.jobPrize.dto.memToCom.proposal.ProposalSummaryForCompanyDto;
import com.jobPrize.dto.memToCom.proposal.ProposalSummaryForMemberDto;

public interface ProposalService {
	void createProposal(Long id, ProposalCreateDto proposalCreateDtodto);
	Page<ProposalSummaryForMemberDto> readProposalForMemberPage(Long id, Pageable pageable);
	Page<ProposalSummaryForCompanyDto> readProposalForCompanyPage(Long id, Pageable pageable);
	ProposalResponseDto readProposal(Long id, Long proposalId);
}
