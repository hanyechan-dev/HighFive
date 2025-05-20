package com.jobPrize.service.memToCom.proposal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.entity.memToCom.Proposal;
import com.jobPrize.memberService.dto.proposal.ProposalResponseDto;
import com.jobPrize.memberService.dto.proposal.ProposalSummaryDto;
import com.jobPrize.repository.memToCom.proposal.ProposalRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ProposalServiceImpl implements ProposalService {
	
	private final ProposalRepository proposalRepository;

	@Override
	public Page<ProposalSummaryDto> getProposalPage(Long id, Pageable pageable) {
		Page<Proposal> proposals = proposalRepository.findAllByMemberId(id, pageable);

		List<ProposalSummaryDto> proposalSummaryDtos = new ArrayList<>();
		for(Proposal proposal : proposals) {
			ProposalSummaryDto proposalSummaryDto = ProposalSummaryDto.from(proposal);
			proposalSummaryDtos.add(proposalSummaryDto);
		}
		
		
		return new PageImpl<ProposalSummaryDto>(proposalSummaryDtos,pageable,proposals.getTotalElements());
	}

	@Override
	public ProposalResponseDto getProposal(Long id, Long proposalId) {
		Proposal proposal = proposalRepository.findById(proposalId)
				.orElseThrow(() -> new IllegalStateException("존재하지 않는 제안입니다."));
		
		if(!proposal.getMember().getId().equals(id)) {
			throw new IllegalStateException("제안의 대상과 회원이 일치하지 않습니다.");
		}
		
		
		return ProposalResponseDto.from(proposal);
	}

}
