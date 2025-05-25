package com.jobPrize.service.memToCom.proposal;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jobPrize.dto.memToCom.proposal.ProposalCreateDto;
import com.jobPrize.dto.memToCom.proposal.ProposalResponseDto;
import com.jobPrize.dto.memToCom.proposal.ProposalSummaryForCompanyDto;
import com.jobPrize.dto.memToCom.proposal.ProposalSummaryForMemberDto;
import com.jobPrize.entity.common.UserType;

public interface ProposalService {
	void createProposal(Long id, UserType userType, ProposalCreateDto proposalCreateDto);
	Page<ProposalSummaryForMemberDto> readProposalForMemberPage(Long id, Pageable pageable);
	Page<ProposalSummaryForCompanyDto> readProposalForCompanyPage(Long id, Pageable pageable);
	ProposalResponseDto readProposal(Long id, UserType userType, Long proposalId);
}
