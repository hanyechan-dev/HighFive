package com.jobPrize.dto.memToCom.proposal;

import com.jobPrize.enumerate.ProposalStatus;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ProposalUpdateDto {
	
	@NotNull(message = "수정 시 id는 필수입니다.")
	Long id;
	
	@NotNull(message = "수정 시 상태는 필수입니다.")
	private ProposalStatus proposalStatus;
	
	
}