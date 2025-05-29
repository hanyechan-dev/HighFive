package com.jobPrize.service.memToCom.interest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.customException.CustomEntityNotFoundException;
import com.jobPrize.dto.company.memberPool.MemberFilterCondition;
import com.jobPrize.dto.company.memberPool.MemberPoolSummaryDto;
import com.jobPrize.dto.memToCom.application.ApplicationSummaryForCompanyDto;
import com.jobPrize.entity.company.Company;
import com.jobPrize.entity.memToCom.Application;
import com.jobPrize.entity.memToCom.EducationLevel;
import com.jobPrize.entity.memToCom.Interest;
import com.jobPrize.entity.memToCom.Similarity;
import com.jobPrize.entity.member.Member;
import com.jobPrize.repository.company.company.CompanyRepository;
import com.jobPrize.repository.memToCom.application.ApplicationRepository;
import com.jobPrize.repository.memToCom.interest.InterestRepository;
import com.jobPrize.repository.memToCom.similarity.SimilarityRepository;
import com.jobPrize.repository.member.member.MemberRepository;
import com.jobPrize.util.MemToComUtil;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class InterestServiceImpl implements InterestService {
	
	private final InterestRepository interestRepository;
	
    private final MemberRepository memberRepository;
    
    private final CompanyRepository companyRepository;
    
    private final SimilarityRepository similarityRepository;
    
    private final MemToComUtil memToComUtil;
    
    private final ApplicationRepository applicationRepository; 

	@Override
	public void toggleInterest(long id, Long memberId) {
		boolean isInterest = interestRepository.existsByCompanyIdAndMemberId(id, memberId);
		
		if(isInterest) {
			Interest interest = interestRepository.findByCompanyIdAndMemberId(id, memberId)
					.orElseThrow(()->new CustomEntityNotFoundException("관심"));
			
			interestRepository.delete(interest);
		}
		else {
			Company company = companyRepository.findById(id)
					.orElseThrow(()->new CustomEntityNotFoundException("기업"));
			Member member = memberRepository.findById(memberId)
					.orElseThrow(()->new CustomEntityNotFoundException("회원"));
	        
			Interest interest = Interest.builder()
					.company(company)
					.member(member)
					.build();
			
			interestRepository.save(interest);
		}
		
	}
	@Override
	@Transactional(readOnly = true)
	public Page<MemberPoolSummaryDto> readInterestedMembers(Long id, MemberFilterCondition memberFilterCondition, Pageable pageable) {
	    Page<Similarity> similarities = similarityRepository.findAllWithMemberByCompanyIdAndCondition(id, memberFilterCondition, pageable);
	    List<MemberPoolSummaryDto> memberPoolSummaryDtos = new ArrayList<>();

	    for (Similarity similarity : similarities) {
	        boolean hasCareer = memToComUtil.hasCareer(similarity);
	        String job = memToComUtil.job(similarity);
	        EducationLevel latestEducationLevel = memToComUtil.latestEducationLevel(similarity);
	        boolean isInterested = memToComUtil.isInterested(similarity); 

	        if (isInterested) { 
	            MemberPoolSummaryDto memberPoolSummaryDto = MemberPoolSummaryDto.of(similarity, hasCareer, job, latestEducationLevel, isInterested);
	            memberPoolSummaryDtos.add(memberPoolSummaryDto);
	        }
	    }

	    return new PageImpl<>(memberPoolSummaryDtos, pageable, similarities.getTotalElements());
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<ApplicationSummaryForCompanyDto> readInterestedApplications(Long jobPostingId, Pageable pageable) {
		
		Page<Application> applications = applicationRepository.findAllByJobPostingId(jobPostingId, pageable);

		
		List<ApplicationSummaryForCompanyDto> applicationSummaryForCompanyDtos = new ArrayList<>();
		
		for(Application application : applications) {

			boolean hasCareer = memToComUtil.hasCareer(application);
			EducationLevel latestEducationLevel = memToComUtil.latestEducationLevel(application);
			boolean isInterested = memToComUtil.isInterested(application);
			boolean isPassed = application.isPassed();
			
			if (isInterested) { 
				ApplicationSummaryForCompanyDto applicationSummaryForCompanyDto = ApplicationSummaryForCompanyDto.of(application, hasCareer, latestEducationLevel, isInterested, isPassed);
				applicationSummaryForCompanyDtos.add(applicationSummaryForCompanyDto);
			}
		}
		
		return new PageImpl<ApplicationSummaryForCompanyDto>(applicationSummaryForCompanyDtos, pageable, applications.getTotalElements() );
	}
	
	
	
}
