package com.jobPrize.dto.memToCom.proposal;

import java.time.LocalDate;

import com.jobPrize.entity.common.GenderType;
import com.jobPrize.entity.common.User;
import com.jobPrize.entity.memToCom.EducationLevel;
import com.jobPrize.entity.memToCom.Proposal;
import com.jobPrize.entity.memToCom.ProposalStatus;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProposalSummaryForCompanyDto {
	
    private Long id;
    
    private String name;
    
    private GenderType genderType;
    
    private LocalDate birthDate;
    
    private boolean hasCareer;
    
    private String job;
    
    private EducationLevel educationLevel;
    
    private LocalDate proposalDate;
    
    private ProposalStatus proposalStatus;

    public static ProposalSummaryForCompanyDto of(Proposal proposal, boolean hasCareer, String job, EducationLevel educationLevel) {
    	User user = proposal.getMember().getUser();
		return ProposalSummaryForCompanyDto.builder()
			.id(proposal.getId())
			.name(user.getName())
			.genderType(user.getGenderType())
			.birthDate(user.getBirthDate())
			.hasCareer(hasCareer)
			.job(job)
			.educationLevel(educationLevel)
			.proposalDate(proposal.getProposalDate())
			.proposalStatus(proposal.getProposalStatus())
			.build();
	}
}
