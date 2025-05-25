package com.jobPrize.service.memToCom.interest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.customException.CustomEntityNotFoundException;
import com.jobPrize.entity.company.Company;
import com.jobPrize.entity.memToCom.Interest;
import com.jobPrize.entity.member.Member;
import com.jobPrize.repository.company.company.CompanyRepository;
import com.jobPrize.repository.memToCom.interest.InterestRepository;
import com.jobPrize.repository.member.member.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class InterestServiceImpl implements InterestService {
	
	private final InterestRepository interestRepository;
	
    private final MemberRepository memberRepository;
    
    private final CompanyRepository companyRepository;

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

}
