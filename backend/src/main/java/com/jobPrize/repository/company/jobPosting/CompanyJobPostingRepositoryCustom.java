package com.jobPrize.repository.company.jobPosting;

import java.util.List;
import java.util.Optional;

import com.jobPrize.entity.company.JobPosting;

public interface CompanyJobPostingRepositoryCustom {
	 List<JobPosting> findAllByCompanyId(Long Id); 
	 Optional<JobPosting> findByCompanyId(Long id);
}
