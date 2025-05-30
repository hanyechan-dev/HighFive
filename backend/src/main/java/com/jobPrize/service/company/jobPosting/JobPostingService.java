package com.jobPrize.service.company.jobPosting;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jobPrize.dto.company.jobPosting.JobPostingCreateDto;
import com.jobPrize.dto.company.jobPosting.JobPostingResponseDto;
import com.jobPrize.dto.company.jobPosting.JobPostingSummaryDto;
import com.jobPrize.dto.company.jobPosting.JobPostingUpdateDto;
import com.jobPrize.dto.company.jobPostingImage.JobPostingImageCreateListDto;
import com.jobPrize.enumerate.ApprovalStatus;
import com.jobPrize.enumerate.UserType;

public interface JobPostingService {

	void createJobPosting(Long id, UserType userType, ApprovalStatus approvalStatus, boolean isSubscribed, JobPostingCreateDto jobPostingCreateDto, JobPostingImageCreateListDto jobPostingImageCreateListDto);

	Page<JobPostingSummaryDto> readJobPostingPage(Long id, Pageable pageable);

	JobPostingResponseDto readJobPosting(Long jobPostingId);

	void updateJobPosting(Long id, UserType userType, ApprovalStatus approvalStatus, boolean isSubscribed,JobPostingUpdateDto jobPostingUpdateDto, JobPostingImageCreateListDto jobPostingImageCreateListDto);
	
	void deleteJobPosting(Long id, Long jobPostingId);

}
