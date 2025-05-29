package com.jobPrize.controller.company;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobPrize.dto.company.jobPosting.JobPostingSummaryDto;
import com.jobPrize.dto.memToCom.application.ApplicationSummaryForCompanyDto;
import com.jobPrize.entity.common.UserType;
import com.jobPrize.service.company.jobPosting.JobPostingService;
import com.jobPrize.service.memToCom.pass.PassService;
import com.jobPrize.util.SecurityUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("passes")
@RequiredArgsConstructor
public class PassController {

	private final PassService passService;
	
	private final JobPostingService jobPostingService;
	
	@GetMapping
	public ResponseEntity<Page<JobPostingSummaryDto>> readMyJobPostings(Pageable pageable) {

		Long id = SecurityUtil.getId();

		Page<JobPostingSummaryDto> jobPostingSummaryDtos = jobPostingService.readJobPostingPage(id, pageable);

		return ResponseEntity.status(HttpStatus.OK).body(jobPostingSummaryDtos);
	}
	
	@GetMapping("/applications")
	public ResponseEntity<Page<ApplicationSummaryForCompanyDto>> readPassedApplications(Long jobPostingId, Pageable pageable){
		
		Page<ApplicationSummaryForCompanyDto> applicationSummaryForCompanyDtos = passService.readPassedApplication(jobPostingId, pageable);
		
		return ResponseEntity.status(HttpStatus.OK).body(applicationSummaryForCompanyDtos);
	}

	

	@PostMapping
	public ResponseEntity<Void> approveApplication(@RequestBody Long applicationId) {
		
		Long companyId = SecurityUtil.getId();
		
		UserType userType = SecurityUtil.getUserType();

		passService.createPass(companyId, userType, applicationId);

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}