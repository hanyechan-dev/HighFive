package com.jobPrize.service.memToCom.proposal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.customException.CustomEntityNotFoundException;
import com.jobPrize.dto.memToCom.proposal.ProposalCreateDto;
import com.jobPrize.dto.memToCom.proposal.ProposalResponseDto;
import com.jobPrize.dto.memToCom.proposal.ProposalSummaryForCompanyDto;
import com.jobPrize.dto.memToCom.proposal.ProposalSummaryForMemberDto;
import com.jobPrize.dto.memToCom.proposal.ProposalUpdateDto;
import com.jobPrize.entity.company.Company;
import com.jobPrize.entity.memToCom.Proposal;
import com.jobPrize.entity.member.Member;
import com.jobPrize.enumerate.ApprovalStatus;
import com.jobPrize.enumerate.EducationLevel;
import com.jobPrize.enumerate.ProposalStatus;
import com.jobPrize.enumerate.UserType;
import com.jobPrize.repository.company.company.CompanyRepository;
import com.jobPrize.repository.memToCom.proposal.ProposalRepository;
import com.jobPrize.repository.member.member.MemberRepository;
import com.jobPrize.util.AssertUtil;
import com.jobPrize.util.MemToComUtil;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ProposalServiceImpl implements ProposalService {
	
	private final ProposalRepository proposalRepository;
	
	private final CompanyRepository companyRepository;
	
	private final MemberRepository memberRepository;
	
	private final MemToComUtil memToComUtil;

	private final AssertUtil assertUtil;

	private final static String ENTITY_NAME = "채용 제안";
	
	@Override
	public void createProposal(Long id, UserType userType, ApprovalStatus approvalStatus, boolean isSubscribed, ProposalCreateDto proposalCreateDto) {

		String action = "발송";
		
		assertUtil.assertForCompany(userType, approvalStatus, isSubscribed, ENTITY_NAME, action);
		
		Company company = companyRepository.findByIdAndDeletedDateIsNull(id)
				.orElseThrow(()-> new CustomEntityNotFoundException("기업"));
		
		Member member = memberRepository.findByIdAndDeletedDateIsNull(proposalCreateDto.getMemberId())
				.orElseThrow(()-> new CustomEntityNotFoundException("회원"));
		
		Proposal proposal = Proposal.of(proposalCreateDto, company, member);
		
		proposalRepository.save(proposal);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Page<ProposalSummaryForMemberDto> readProposalForMemberPage(Long id, Pageable pageable) {
		Page<Proposal> proposals = proposalRepository.findAllByMemberId(id, pageable);

		List<ProposalSummaryForMemberDto> proposalSummaryDtos = new ArrayList<>();
		for(Proposal proposal : proposals) {
			ProposalSummaryForMemberDto proposalSummaryDto = ProposalSummaryForMemberDto.from(proposal);
			proposalSummaryDtos.add(proposalSummaryDto);
		}
		
		
		return new PageImpl<ProposalSummaryForMemberDto>(proposalSummaryDtos,pageable,proposals.getTotalElements());
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<ProposalSummaryForCompanyDto> readProposalForCompanyPage(Long id, Pageable pageable) {
		
		Page<Proposal> proposals = proposalRepository.findAllByCompanyId(id, pageable);

		List<ProposalSummaryForCompanyDto> proposalSummaryDtos = new ArrayList<>();
		for(Proposal proposal : proposals) {
			
			boolean hasCareer = memToComUtil.hasCareer(proposal);
			EducationLevel latestEducationLevel = memToComUtil.latestEducationLevel(proposal);
			
			
			ProposalSummaryForCompanyDto proposalSummaryDto = ProposalSummaryForCompanyDto.of(proposal, hasCareer, latestEducationLevel);
			proposalSummaryDtos.add(proposalSummaryDto);
		}
		
		return new PageImpl<ProposalSummaryForCompanyDto>(proposalSummaryDtos,pageable,proposals.getTotalElements());
	}

	@Override
	@Transactional(readOnly = true)
	public ProposalResponseDto readProposal(Long id, UserType userType, Long proposalId) {
		
		String action = "조회";
		
		assertUtil.assertUserType(userType, UserType.일반회원, UserType.기업회원, ENTITY_NAME, action);
		
		Proposal proposal = proposalRepository.findById(proposalId)
				.orElseThrow(() -> new CustomEntityNotFoundException(ENTITY_NAME));
		
		if(UserType.기업회원.equals(userType)) {

			Long companyId = proposalRepository.findCompanyIdByProposalId(proposalId)
				.orElseThrow(() -> new CustomEntityNotFoundException("소유자"));

			assertUtil.assertId(id, companyId, ENTITY_NAME, action);
        }
        else if(UserType.일반회원.equals(userType)) {

			Long memberId = proposalRepository.findMemberIdByProposalId(proposalId)
				.orElseThrow(() -> new CustomEntityNotFoundException("소유자"));

			assertUtil.assertId(id, memberId, ENTITY_NAME, action);
        }
		
		
		return ProposalResponseDto.from(proposal);
	}

	@Override
	public void updateProposal(Long id, UserType userType, ProposalUpdateDto proposalUpdateDto) {
		
		String action = proposalUpdateDto.getProposalStatus();
		
		assertUtil.assertUserType(userType, UserType.일반회원, ENTITY_NAME, action);

		Long proposalId = proposalUpdateDto.getId();
		
		Proposal proposal = proposalRepository.findById(proposalId)
				.orElseThrow(() -> new CustomEntityNotFoundException(ENTITY_NAME));
		
		Long ownerId = proposalRepository.findMemberIdByProposalId(proposalId)
				.orElseThrow(() -> new CustomEntityNotFoundException("소유자"));

		assertUtil.assertId(id, ownerId, ENTITY_NAME, action);
		
		proposal.changeStatus(ProposalStatus.valueOf(proposalUpdateDto.getProposalStatus()));
		
	}
	




}
