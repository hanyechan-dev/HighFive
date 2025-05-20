package com.jobPrize.service.memToCom.application;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jobPrize.entity.common.UserType;
import com.jobPrize.memberService.dto.application.ApplicationCreateDto;
import com.jobPrize.memberService.dto.application.ApplicationResponseDto;
import com.jobPrize.memberService.dto.application.ApplicationSummaryDto;

public interface ApplicationService {
	Page<ApplicationSummaryDto> getApplicationPage(Long id, Pageable pageable);
	ApplicationResponseDto getApplication(Long id, Long applicationId);
	void createApplication(Long id, UserType userType, ApplicationCreateDto applicationCreateDto);
}
