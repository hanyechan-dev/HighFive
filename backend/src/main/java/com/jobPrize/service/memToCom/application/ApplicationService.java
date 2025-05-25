package com.jobPrize.service.memToCom.application;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jobPrize.dto.memToCom.application.ApplicationCreateDto;
import com.jobPrize.dto.memToCom.application.ApplicationResponseDto;
import com.jobPrize.dto.memToCom.application.ApplicationSummaryForCompanyDto;
import com.jobPrize.dto.memToCom.application.ApplicationSummaryForMemberDto;
import com.jobPrize.entity.common.UserType;

public interface ApplicationService {
	void createApplication(Long id, UserType userType, ApplicationCreateDto applicationCreateDto);
	Page<ApplicationSummaryForMemberDto> readApplicationForMemberPage(Long id, Pageable pageable);
	Page<ApplicationSummaryForCompanyDto> readApplicationForCompanyPage(Long jobPostingId, Pageable pageable);
	ApplicationResponseDto readApplication(Long id, UserType userType, Long applicationId);
	
}
