package com.jobPrize.service.memToCom.pass;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.customException.CustomEntityNotFoundException;
import com.jobPrize.dto.memToCom.application.ApplicationSummaryForCompanyDto;
import com.jobPrize.entity.common.UserType;
import com.jobPrize.entity.memToCom.Application;
import com.jobPrize.entity.memToCom.EducationLevel;
import com.jobPrize.entity.memToCom.Pass;
import com.jobPrize.repository.memToCom.application.ApplicationRepository;
import com.jobPrize.repository.memToCom.pass.PassRepository;
import com.jobPrize.util.AssertUtil;
import com.jobPrize.util.MemToComUtil;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PassServiceImpl implements PassService {

	private final ApplicationRepository applicationRepository;

	private final PassRepository passRepository;

	private final AssertUtil assertUtil;

	private final MemToComUtil memToComUtil;

	@Override
	public void createPass(Long id, UserType userType, Long applicationId) {

		assertUtil.assertUserType(userType, UserType.기업회원, "합격");

		if (passRepository.existsByApplicationId(applicationId)) {
			throw new IllegalArgumentException("이미 합격된 지원서입니다.");
		}

		Application application = applicationRepository.findById(applicationId)
				.orElseThrow(() -> new CustomEntityNotFoundException("지원서"));

		assertUtil.assertId(id, application, "합격");

		Pass pass = Pass.builder().application(application).build();

		passRepository.save(pass);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<ApplicationSummaryForCompanyDto> readPassedApplication(Long jobPostingId, Pageable pageable) { 
	    Page<Application> applications = applicationRepository.findAllByJobPostingId(jobPostingId, pageable); 

	    List<ApplicationSummaryForCompanyDto> applicationSummaryForCompanyDtos = new ArrayList<>();

	    for (Application application : applications) {
	        boolean hasCareer = memToComUtil.hasCareer(application);
	        EducationLevel latestEducationLevel = memToComUtil.latestEducationLevel(application);
	        boolean isInterested = memToComUtil.isInterested(application);
	        boolean isPassed = application.isPassed();

	        if (isPassed) { 
	            ApplicationSummaryForCompanyDto applicationSummaryForCompanyDto = ApplicationSummaryForCompanyDto.of(application, hasCareer, latestEducationLevel, isInterested, isPassed);
	            applicationSummaryForCompanyDtos.add(applicationSummaryForCompanyDto);
	        }
	    }

	    return new PageImpl<>(applicationSummaryForCompanyDtos, pageable, applications.getTotalElements());
	}
}
