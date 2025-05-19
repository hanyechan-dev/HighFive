package com.jobPrize.memberService.service.application;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jobPrize.memberService.dto.application.ApplicationCreateDto;
import com.jobPrize.memberService.dto.application.ApplicationResponseDto;
import com.jobPrize.memberService.dto.application.ApplicationSummaryDto;

public interface ApplicationService {
	Page<ApplicationSummaryDto> getListApplication(String token, Pageable pageable);
	ApplicationResponseDto getApplication(String token, Long applicationId);
	void createApplication(String token, ApplicationCreateDto applicationCreateDto);
}
