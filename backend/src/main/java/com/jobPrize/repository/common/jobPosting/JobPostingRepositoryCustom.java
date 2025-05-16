package com.jobPrize.repository.common.jobPosting;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jobPrize.entity.company.JobPosting;
import com.jobPrize.memberService.dto.jobPositing.JobPostingFilterCondition;

public interface JobPostingRepositoryCustom {
	Page<JobPosting> findAllByCondition(JobPostingFilterCondition condition, Pageable pageable);
}
