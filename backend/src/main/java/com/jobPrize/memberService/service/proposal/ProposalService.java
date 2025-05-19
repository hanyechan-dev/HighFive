package com.jobPrize.memberService.service.proposal;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jobPrize.memberService.dto.proposal.ProposalResponseDto;
import com.jobPrize.memberService.dto.proposal.ProposalSummaryDto;

public interface ProposalService {
	Page<ProposalSummaryDto> getListProposal(String token, Pageable pageable);
	ProposalResponseDto getProposal(String token, Long proposalId);
}
