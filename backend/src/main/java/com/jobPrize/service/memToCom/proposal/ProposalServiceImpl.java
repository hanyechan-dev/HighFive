package com.jobPrize.service.memToCom.proposal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.customException.CustomAccessDeniedException;
import com.jobPrize.customException.CustomEntityNotFoundException;
import com.jobPrize.customException.CustomOwnerMismatchException;
import com.jobPrize.dto.memToCom.proposal.ProposalCreateDto;
import com.jobPrize.dto.memToCom.proposal.ProposalResponseDto;
import com.jobPrize.dto.memToCom.proposal.ProposalSummaryForCompanyDto;
import com.jobPrize.dto.memToCom.proposal.ProposalSummaryForMemberDto;
import com.jobPrize.entity.common.UserType;
import com.jobPrize.entity.company.Company;
import com.jobPrize.entity.memToCom.EducationLevel;
import com.jobPrize.entity.memToCom.Proposal;
import com.jobPrize.entity.member.Member;
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
	
	@Override
	public void createProposal(Long id, UserType userType, ProposalCreateDto proposalCreateDto) {

		assertUtil.assertUserType(userType, UserType.기업회원, "제안 등록");
		
		Company company = companyRepository.findById(id)
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
		
		assertUtil.assertUserType(userType,UserType.일반회원,UserType.기업회원,"조회");
		
		Proposal proposal = proposalRepository.findById(proposalId)
				.orElseThrow(() -> new CustomEntityNotFoundException("제안"));
		
		if(UserType.기업회원.equals(userType)) {
        	if(!proposal.getCompany().getId().equals(id)) {
        		throw new CustomOwnerMismatchException("proposal", "조회");
        	}
        }
        else if(UserType.일반회원.equals(userType)) {
        	if(!proposal.getMember().getId().equals(id)) {
        		throw new CustomOwnerMismatchException("proposal", "조회");
        	}
        }
		
		
		return ProposalResponseDto.from(proposal);
	}
	




}
