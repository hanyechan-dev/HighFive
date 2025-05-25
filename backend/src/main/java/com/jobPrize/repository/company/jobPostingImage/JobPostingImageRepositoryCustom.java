package com.jobPrize.repository.company.jobPostingImage;

import java.util.List;

import com.jobPrize.entity.company.JobPostingImage;

public interface JobPostingImageRepositoryCustom {
	List<JobPostingImage> findAllByJobPostingId(Long Id);
}
