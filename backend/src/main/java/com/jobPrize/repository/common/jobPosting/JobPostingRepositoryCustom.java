package com.jobPrize.repository.common.jobPosting;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jobPrize.dto.memToCom.jobPosting.JobPostingFilterCondition;
import com.jobPrize.entity.company.JobPosting;

public interface JobPostingRepositoryCustom {
	Page<JobPosting> findAllByCondition(JobPostingFilterCondition condition, Pageable pageable);
}
