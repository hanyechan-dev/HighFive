package com.jobPrize.controller.memToCom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobPrize.dto.common.id.IdDto;
import com.jobPrize.dto.memToCom.proposal.ProposalResponseDto;
import com.jobPrize.dto.memToCom.proposal.ProposalSummaryForMemberDto;
import com.jobPrize.dto.memToCom.proposal.ProposalUpdateDto;
import com.jobPrize.enumerate.UserType;
import com.jobPrize.service.memToCom.proposal.ProposalService;
import com.jobPrize.util.SecurityUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("members/proposals")
@RequiredArgsConstructor
public class MemberProposalController {

    private final ProposalService proposalService;

    @GetMapping
    public ResponseEntity<Page<ProposalSummaryForMemberDto>> readMyProposals(Pageable pageable) {

        Long id = SecurityUtil.getId();

        Page<ProposalSummaryForMemberDto> proposalSummaryForMemberDtos = proposalService.readProposalForMemberPage(id, pageable);

        return ResponseEntity.status(HttpStatus.OK).body(proposalSummaryForMemberDtos);
    }

    @PostMapping("/detail")
    public ResponseEntity<ProposalResponseDto> readMyProposal(@RequestBody @Valid IdDto idDto) {

        Long id = SecurityUtil.getId();
        
        UserType userType = SecurityUtil.getUserType();

        ProposalResponseDto proposalResponseDto = proposalService.readProposal(id, userType, idDto.getId());

        return ResponseEntity.status(HttpStatus.OK).body(proposalResponseDto);
    }

    @PutMapping
    public ResponseEntity<ProposalResponseDto> updateProposal(@RequestBody @Valid ProposalUpdateDto proposalUpdateDto) {

        Long id = SecurityUtil.getId();
        
        UserType userType = SecurityUtil.getUserType();

        ProposalResponseDto proposalResponseDto = proposalService.updateProposal(id, userType, proposalUpdateDto);

        return ResponseEntity.status(HttpStatus.OK).body(proposalResponseDto);
    }

}
