package com.jobPrize.memberService.service.proposal;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jobPrize.memberService.dto.proposal.ProposalResponseDto;
import com.jobPrize.memberService.dto.proposal.ProposalSummaryDto;

public interface ProposalService {
	Page<ProposalSummaryDto> getProposalPage(Long id, Pageable pageable);
	ProposalResponseDto getProposal(Long id, Long proposalId);
}
