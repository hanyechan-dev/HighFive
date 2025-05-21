package com.jobPrize.dto.memToCom.proposal;

import java.time.LocalDate;

import com.jobPrize.entity.memToCom.Proposal;
import com.jobPrize.entity.memToCom.ProposalStatus;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProposalSummaryDto {
	
	private Long id;
	
	private String proposalTitle;
	
	private String companyName;

	private ProposalStatus proposalStatus;

	private LocalDate proposalDate;
	
	public static ProposalSummaryDto from(Proposal proposal) {
		return ProposalSummaryDto.builder()
			.id(proposal.getId())
			.proposalTitle(proposal.getProposalTitle())
			.companyName(proposal.getCompany().getCompanyName())
			.proposalStatus(proposal.getProposalStatus())
			.proposalDate(proposal.getProposalDate())
			.build();
	}
	
	
}
