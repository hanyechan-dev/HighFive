package com.jobPrize.controller.company;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobPrize.dto.common.DeleteIdDto;
import com.jobPrize.dto.company.jobPosting.JobPostingCreateDto;
import com.jobPrize.dto.company.jobPosting.JobPostingSummaryDto;
import com.jobPrize.dto.company.jobPosting.JobPostingUpdateDto;
import com.jobPrize.entity.common.UserType;
import com.jobPrize.service.company.jobPosting.JobPostingService;
import com.jobPrize.util.SecurityUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("company/jobPostings")
@RequiredArgsConstructor
public class JobPostingController {

	private final JobPostingService jobPostingService;

	@PostMapping
	public ResponseEntity<Page<JobPostingSummaryDto>> readMyJobPostings(Pageable pageable) {

		Long id = SecurityUtil.getId();

		Page<JobPostingSummaryDto> jobPostingSummaryDtos = jobPostingService.readJobPostingPage(id, pageable);

		return ResponseEntity.status(HttpStatus.OK).body(jobPostingSummaryDtos);
	}

	@PutMapping
	public ResponseEntity<Void> updateMyJobPosting(@RequestBody JobPostingUpdateDto jobPostingUpdateDto) {

		Long id = SecurityUtil.getId();

		jobPostingService.updateJobPosting(id, jobPostingUpdateDto);

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@PostMapping
	public ResponseEntity<Void> createMyJopPosting(@RequestBody JobPostingCreateDto jobPostingCreateDto) {

		Long id = SecurityUtil.getId();

		UserType userType = SecurityUtil.getUserType();

		jobPostingService.createJobPosting(id, userType, jobPostingCreateDto);

		return ResponseEntity.status(HttpStatus.CREATED).build();

	}

	@PostMapping("/deletion")
	public ResponseEntity<Void> deletMyJobPosting(@RequestBody DeleteIdDto deleteIdDto) {

		Long id = SecurityUtil.getId();

		jobPostingService.deleteJobPosting(id, deleteIdDto.getId());

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
