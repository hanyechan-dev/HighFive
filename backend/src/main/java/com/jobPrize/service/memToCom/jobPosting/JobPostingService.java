package com.jobPrize.service.memToCom.jobPosting;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jobPrize.dto.memToCom.jobPosting.JobPostingFilterCondition;
import com.jobPrize.dto.memToCom.jobPosting.JobPostingSummaryByConditionDto;
import com.jobPrize.dto.memToCom.jobPosting.JobPostingSummaryBySimilarityDto;

public interface JobPostingService {
	Page<JobPostingSummaryByConditionDto> readJobPostingPageByCondition(JobPostingFilterCondition jobPostingFilterCondition, Pageable pageable);
	Page<JobPostingSummaryBySimilarityDto> readJobPostingPageBySimilarity(Long id, Pageable pageable);
}
