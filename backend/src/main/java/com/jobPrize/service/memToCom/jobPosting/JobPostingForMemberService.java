package com.jobPrize.service.memToCom.jobPosting;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jobPrize.dto.memToCom.jobPosting.JobPostingFilterCondition;
import com.jobPrize.dto.memToCom.jobPosting.JobPostingForMemberResponseDto;
import com.jobPrize.dto.memToCom.jobPosting.JobPostingMainCardDto;
import com.jobPrize.dto.memToCom.jobPosting.JobPostingSummaryForMemberDto;
import com.jobPrize.dto.memToCom.jobPosting.JobPostingUnderCardDto;

public interface JobPostingForMemberService {
	List<JobPostingMainCardDto> readJobPostingMainCardListByMemberId(Long id);
	List<JobPostingUnderCardDto> readJobPostingUnderCardListByMemberId(Long id);
	Page<JobPostingSummaryForMemberDto> readJobPostingPageByMemberIdAndCondition(Long id,JobPostingFilterCondition condition, Pageable pageable);
	
	JobPostingForMemberResponseDto readJobPosting(Long jobPostingid);
}
