package com.jobPrize.memberService.dto.proposal;

import java.time.LocalDate;

import com.jobPrize.entity.memToCom.Proposal;
import com.jobPrize.entity.memToCom.ProposalStatus;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProposalResponseDto {

	private Long id;
	
	private String proposalTitle;
	
	private String companyName;
	
	private String proposalContent;
	
	private String proposalJob;
	
	private int proposalSalary;
	
	private LocalDate proposalDate;
	
	private ProposalStatus proposalStatus;
	

	public static ProposalResponseDto from(Proposal proposal) {
		return ProposalResponseDto.builder()
			.id(proposal.getId())
			.proposalTitle(proposal.getProposalTitle())
			.companyName(proposal.getCompany().getCompanyName())
			.proposalContent(proposal.getProposalContent())
			.proposalJob(proposal.getProposalJob())
			.proposalSalary(proposal.getProposalSalary())
			.proposalDate(proposal.getProposalDate())
			.proposalStatus(proposal.getProposalStatus())
			.build();
	}
	
}
