package com.jobPrize.controller.company;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobPrize.dto.memToCom.application.ApplicationResponseDto;
import com.jobPrize.dto.memToCom.application.ApplicationSummaryForCompanyDto;
import com.jobPrize.entity.common.UserType;
import com.jobPrize.service.memToCom.application.ApplicationService;
import com.jobPrize.util.SecurityUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("company/applications")
@RequiredArgsConstructor
public class CompanyApplicationController {
	private final ApplicationService applicationService;

	@GetMapping
	public ResponseEntity<Page<ApplicationSummaryForCompanyDto>> readApplications(Pageable pageable) {

		Long id = SecurityUtil.getId();

		Page<ApplicationSummaryForCompanyDto> applicationSummaryForCompanyDtos = applicationService.readApplicationForCompanyPage(id, pageable);

		return ResponseEntity.status(HttpStatus.OK).body(applicationSummaryForCompanyDtos);
	}
	
	@GetMapping
	public ResponseEntity<ApplicationResponseDto> readDetailApplication(@RequestParam Long memberId){

		Long companyId = SecurityUtil.getId();
		
		UserType userType = SecurityUtil.getUserType();
		
		ApplicationResponseDto applicationResponseDto = applicationService.readApplication(companyId, userType, memberId);
		
		return ResponseEntity.status(HttpStatus.OK).body(applicationResponseDto);
		
	}
	
}