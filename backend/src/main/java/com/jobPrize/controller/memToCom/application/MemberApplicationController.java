package com.jobPrize.controller.memToCom.application;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobPrize.dto.common.read.ReadIdDto;
import com.jobPrize.dto.memToCom.application.ApplicationResponseDto;
import com.jobPrize.dto.memToCom.application.ApplicationSummaryForMemberDto;
import com.jobPrize.entity.common.UserType;
import com.jobPrize.service.memToCom.application.ApplicationService;
import com.jobPrize.util.SecurityUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("members/applications")
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
	public ResponseEntity<ApplicationResponseDto> readMyApplication(@RequestBody ReadIdDto readIdDto) {

		Long id = SecurityUtil.getId();

		UserType userType = SecurityUtil.getUserType();

		ApplicationResponseDto applicationResponseDto = applicationService.readApplication(id, userType,readIdDto.getId());
		
		return ResponseEntity.status(HttpStatus.OK).body(applicationResponseDto);
	}

}
