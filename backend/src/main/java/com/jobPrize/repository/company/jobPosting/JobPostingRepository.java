package com.jobPrize.repository.company.jobPosting;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobPrize.entity.company.JobPosting;
import com.jobPrize.enumerate.EmbeddingStatus;

public interface JobPostingRepository extends JpaRepository<JobPosting, Long>,JobPostingRepositoryCustom{
	List<JobPosting> findAllByEmbeddingStatus(EmbeddingStatus embeddingStatus);
}
