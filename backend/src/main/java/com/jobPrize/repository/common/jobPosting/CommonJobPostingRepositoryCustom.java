package com.jobPrize.repository.common.jobPosting;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jobPrize.dummy.JobPostingFilterCondition;
import com.jobPrize.entity.company.JobPosting;

public interface CommonJobPostingRepositoryCustom {
	Page<JobPosting> findAllByCondition(JobPostingFilterCondition condition, Pageable pageable);
}
