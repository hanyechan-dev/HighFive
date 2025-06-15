package com.jobPrize.service.memToCom.jobPosting;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jobPrize.dto.memToCom.jobPosting.JobPostingFilterCondition;
import com.jobPrize.dto.memToCom.jobPosting.JobPostingForMemberResponseDto;
import com.jobPrize.dto.memToCom.jobPosting.JobPostingSummaryForMemberDto;

public interface JobPostingForMemberService {
	Page<JobPostingSummaryForMemberDto> readJobPostingPageByMemberIdAndCondition(Long id,JobPostingFilterCondition condition, Pageable pageable);
	
	JobPostingForMemberResponseDto readJobPosting(Long jobPostingid);
}
