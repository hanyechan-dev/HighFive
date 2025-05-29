package com.jobPrize.service.memToCom.pass;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jobPrize.dto.memToCom.application.ApplicationSummaryForCompanyDto;
import com.jobPrize.entity.common.UserType;

public interface PassService {
	void createPass(Long id, UserType userType, Long applicationId);
	Page<ApplicationSummaryForCompanyDto> readPassedApplication(Long jobPostingId, Pageable pageable);
}
