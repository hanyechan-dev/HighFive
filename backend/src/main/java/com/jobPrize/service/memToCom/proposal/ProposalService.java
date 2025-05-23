package com.jobPrize.service.memToCom.proposal;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jobPrize.dto.memToCom.proposal.ProposalResponseDto;
import com.jobPrize.dto.memToCom.proposal.ProposalSummaryForMemberDto;

public interface ProposalService {
	Page<ProposalSummaryForMemberDto> readProposalPage(Long id, Pageable pageable);
	ProposalResponseDto readProposal(Long id, Long proposalId);
}
