package com.jobPrize.controller.company;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobPrize.dto.common.id.IdDto;
import com.jobPrize.dto.memToCom.proposal.ProposalCreateDto;
import com.jobPrize.dto.memToCom.proposal.ProposalResponseDto;
import com.jobPrize.dto.memToCom.proposal.ProposalSummaryForCompanyDto;
import com.jobPrize.entity.common.UserType;
import com.jobPrize.service.memToCom.proposal.ProposalService;
import com.jobPrize.util.SecurityUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("companies/proposals")
@RequiredArgsConstructor
public class ProposalController {

	private final ProposalService proposalService;
	
	@GetMapping
	public ResponseEntity<Page<ProposalSummaryForCompanyDto>> readMyProposals(Pageable pageable) {

		Long id = SecurityUtil.getId();

		Page<ProposalSummaryForCompanyDto>  proposalSummaryForCompanyDtos = proposalService.readProposalForCompanyPage(id, pageable);

		return ResponseEntity.status(HttpStatus.OK).body(proposalSummaryForCompanyDtos);
	}
	
	 @PostMapping("/detail")
	 public ResponseEntity<ProposalResponseDto> readMyProposal(@RequestBody @Valid IdDto IdDto) {
		 
		 Long id = SecurityUtil.getId();
	        
		 UserType userType = SecurityUtil.getUserType();

		 ProposalResponseDto proposalResponseDto = proposalService.readProposal(id, userType, IdDto.getId());

		 return ResponseEntity.status(HttpStatus.OK).body(proposalResponseDto);
		 
	 }
	
	
	@PostMapping
	public ResponseEntity<Void> createProposal (@RequestBody @Valid ProposalCreateDto proposalCreateDto){
		
		Long id = SecurityUtil.getId();
		
		UserType userType = SecurityUtil.getUserType();
		
		proposalService.createProposal(id, userType, proposalCreateDto);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
