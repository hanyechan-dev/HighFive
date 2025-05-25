package com.jobPrize.repository.company.jobPostingImage;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobPrize.entity.company.JobPostingImage;
public interface JobPostingImageRepository  extends JpaRepository<JobPostingImage, Long>,JobPostingImageRepositoryCustom{

}
