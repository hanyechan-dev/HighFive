package com.jobPrize.service.memToCom.interest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jobPrize.dto.company.memberPool.MemberFilterCondition;
import com.jobPrize.dto.company.memberPool.MemberPoolSummaryDto;
import com.jobPrize.dto.memToCom.application.ApplicationSummaryForCompanyDto;

public interface InterestService {
	void toggleInterest(long id, Long memberId);
	Page<MemberPoolSummaryDto> readInterestedMembers(Long id, MemberFilterCondition memberFilterCondition, Pageable pageable);
	Page<ApplicationSummaryForCompanyDto> readInterestedApplications(Long jobPostingId, Pageable pageable);

}
