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

import com.jobPrize.dto.common.id.IdDto;
import com.jobPrize.dto.company.jobPosting.JobPostingSummaryDto;

import com.jobPrize.dto.memToCom.application.ApplicationResponseForCompanyDto;
import com.jobPrize.dto.memToCom.application.ApplicationSummaryForCompanyDto;

import com.jobPrize.enumerate.UserType;
import com.jobPrize.service.company.jobPosting.JobPostingService;
import com.jobPrize.service.memToCom.application.ApplicationService;
import com.jobPrize.util.SecurityUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/passes")
@RequiredArgsConstructor
public class PassController {

	private final ApplicationService applicationService;

	private final JobPostingService jobPostingService;

	@GetMapping
	public ResponseEntity<Page<JobPostingSummaryDto>> readMyJobPostings(Pageable pageable) {

		Long id = SecurityUtil.getId();

		Page<JobPostingSummaryDto> jobPostingSummaryDtos = jobPostingService.readJobPostingPage(id, pageable);

		return ResponseEntity.status(HttpStatus.OK).body(jobPostingSummaryDtos);
	}

	@PostMapping("/applications")
	public ResponseEntity<Page<ApplicationSummaryForCompanyDto>> readPassedApplications(@RequestBody @Valid IdDto IdDto,
			Pageable pageable) {

		Long id = SecurityUtil.getId();

		Page<ApplicationSummaryForCompanyDto> applicationSummaryForCompanyDtos = applicationService
				.readPassedApplicationPage(id, IdDto.getId(), pageable);

		return ResponseEntity.status(HttpStatus.OK).body(applicationSummaryForCompanyDtos);
	}

	@PostMapping("/applications/detail")
	public ResponseEntity<ApplicationResponseForCompanyDto> readMyApplication(@RequestBody @Valid IdDto idDto) {

		Long id = SecurityUtil.getId();

		UserType userType = SecurityUtil.getUserType();

		ApplicationResponseForCompanyDto applicationResponseForCompanyDto = applicationService
				.readApplicationForCompany(id, userType, idDto.getId());

		return ResponseEntity.status(HttpStatus.OK).body(applicationResponseForCompanyDto);
	}

}