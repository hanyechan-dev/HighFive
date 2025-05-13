package com.jobPrize.repository.common.jobPosting;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobPrize.entity.company.JobPosting;

public interface JobPostingRepository extends JpaRepository<JobPosting, Long>, JobPostingRepositoryCustom{

}
