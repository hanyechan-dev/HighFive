package com.jobPrize.dto.memToCom.proposal;

import java.time.LocalDate;

import com.jobPrize.entity.memToCom.Proposal;
import com.jobPrize.enumerate.ProposalStatus;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProposalSummaryForMemberDto {
	
	private Long id;
	
	private String proposalTitle;
	
	private String companyName;

	private String proposalStatus;

	private LocalDate proposalDate;
	
	public static ProposalSummaryForMemberDto from(Proposal proposal) {
		return ProposalSummaryForMemberDto.builder()
			.id(proposal.getId())
			.proposalTitle(proposal.getProposalTitle())
			.companyName(proposal.getCompany().getCompanyName())
			.proposalStatus(proposal.getProposalStatus().name())
			.proposalDate(proposal.getProposalDate())
			.build();
	}
	
	
}
