package com.jobPrize.controller.company;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobPrize.dto.common.id.IdDto;
import com.jobPrize.dto.company.jobPosting.JobPostingCreateDto;
import com.jobPrize.dto.company.jobPosting.JobPostingResponseDto;
import com.jobPrize.dto.company.jobPosting.JobPostingSummaryDto;
import com.jobPrize.dto.company.jobPosting.JobPostingUpdateDto;
import com.jobPrize.dto.company.jobPostingImage.JobPostingImageCreateListDto;
import com.jobPrize.enumerate.ApprovalStatus;
import com.jobPrize.enumerate.UserType;
import com.jobPrize.service.company.jobPosting.JobPostingService;
import com.jobPrize.util.SecurityUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/companies/jobPostings")
@RequiredArgsConstructor
public class JobPostingController {

	private final JobPostingService jobPostingService;

	@GetMapping
	public ResponseEntity<Page<JobPostingSummaryDto>> readMyJobPostings(Pageable pageable) {

		Long id = SecurityUtil.getId();

		Page<JobPostingSummaryDto> jobPostingSummaryDtos = jobPostingService.readJobPostingPage(id, pageable);

		return ResponseEntity.status(HttpStatus.OK).body(jobPostingSummaryDtos);
	}
	
	@PostMapping("/detail")
	public ResponseEntity<JobPostingResponseDto> readMyJobPosting(@RequestBody @Valid IdDto IdDto){
		
		JobPostingResponseDto jobPostingResponseDto = jobPostingService.readJobPosting(IdDto.getId());
		
		return ResponseEntity.status(HttpStatus.OK).body(jobPostingResponseDto);
	}
	
	
	
	@PostMapping
	public ResponseEntity<Void> createMyJopPosting(@ModelAttribute @Valid JobPostingCreateDto jobPostingCreateDto,
	        @ModelAttribute @Valid JobPostingImageCreateListDto jobPostingImageCreateListDto) {

		Long id = SecurityUtil.getId();

		UserType userType = SecurityUtil.getUserType();
		
		ApprovalStatus approvalStatus = SecurityUtil.getApprovalStatus();

		boolean isSubscribed = SecurityUtil.isSubscribed();

		jobPostingService.createJobPosting(id, userType, approvalStatus, isSubscribed, jobPostingCreateDto, jobPostingImageCreateListDto);

		return ResponseEntity.status(HttpStatus.CREATED).build();

	}

	@PutMapping
	public ResponseEntity<Void> updateMyJobPosting(@ModelAttribute @Valid JobPostingUpdateDto jobPostingUpdateDto,
	        @ModelAttribute @Valid JobPostingImageCreateListDto jobPostingImageCreateListDto) {

		Long id = SecurityUtil.getId();
		
		UserType userType = SecurityUtil.getUserType();
		
		ApprovalStatus approvalStatus = SecurityUtil.getApprovalStatus();

		boolean isSubscribed = SecurityUtil.isSubscribed();

		jobPostingService.updateJobPosting(id, userType, approvalStatus, isSubscribed, jobPostingUpdateDto, jobPostingImageCreateListDto);

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}


	@PostMapping("/deletion")
	public ResponseEntity<Void> deletMyJobPosting(@RequestBody @Valid IdDto IdDto) {

		Long id = SecurityUtil.getId();

		jobPostingService.deleteJobPosting(id, IdDto.getId());

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
