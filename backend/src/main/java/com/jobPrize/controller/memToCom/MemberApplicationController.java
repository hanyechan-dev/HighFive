package com.jobPrize.controller.memToCom;

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
import com.jobPrize.dto.memToCom.application.ApplicationCreateDto;
import com.jobPrize.dto.memToCom.application.ApplicationResponseDto;
import com.jobPrize.dto.memToCom.application.ApplicationSummaryForMemberDto;
import com.jobPrize.enumerate.UserType;
import com.jobPrize.service.memToCom.application.ApplicationService;
import com.jobPrize.util.SecurityUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/members/applications")
@RequiredArgsConstructor
public class MemberApplicationController {

	private final ApplicationService applicationService;

	@GetMapping
	public ResponseEntity<Page<ApplicationSummaryForMemberDto>> readMyApplications(Pageable pageable) {

		Long id = SecurityUtil.getId();

		Page<ApplicationSummaryForMemberDto> applicationSummaryForMemberDtos = applicationService.readApplicationForMemberPage(id, pageable);

		return ResponseEntity.status(HttpStatus.OK).body(applicationSummaryForMemberDtos);
	}

	@PostMapping("/detail")
	public ResponseEntity<ApplicationResponseDto> readMyApplication(@RequestBody @Valid IdDto idDto) {

		Long id = SecurityUtil.getId();

		UserType userType = SecurityUtil.getUserType();

		ApplicationResponseDto applicationResponseDto = applicationService.readApplication(id, userType,idDto.getId());
		
		return ResponseEntity.status(HttpStatus.OK).body(applicationResponseDto);
	}
	
	@PostMapping
	public ResponseEntity<Void> createApplication(@RequestBody @Valid ApplicationCreateDto applicationCreateDto) {

		Long id = SecurityUtil.getId();

		UserType userType = SecurityUtil.getUserType();

		applicationService.createApplication(id, userType, applicationCreateDto);

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

}
