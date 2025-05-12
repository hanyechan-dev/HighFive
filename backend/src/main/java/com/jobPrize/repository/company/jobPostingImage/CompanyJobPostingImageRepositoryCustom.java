package com.jobPrize.repository.company.jobPostingImage;
import java.util.List;


	import com.jobPrize.entity.company.JobPosting;
import com.jobPrize.entity.company.JobPostingImage;

	public interface CompanyJobPostingImageRepositoryCustom {
		 List<JobPostingImage> findAllByJobPostingId(Long Id); 
	}


