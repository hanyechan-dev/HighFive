package com.jobPrize.service.company.jobPostingImage;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.jobPrize.entity.company.JobPosting;

public interface JobPostingImageService {
	void createImages(JobPosting jobPosting, List<MultipartFile> newFiles);
}
