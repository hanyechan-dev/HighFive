package com.jobPrize.repository.company.jobPosting;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jobPrize.entity.company.JobPosting;

public interface CompanyJobPostingRepository extends JpaRepository<JobPosting, Long>,CompanyJobPostingRepositoryCustom{

}
