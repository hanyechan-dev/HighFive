package com.jobPrize.service.memToCom.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.customException.CustomEntityNotFoundException;
import com.jobPrize.customException.CustomOwnerMismatchException;
import com.jobPrize.dto.memToCom.application.ApplicationCreateDto;
import com.jobPrize.dto.memToCom.application.ApplicationResponseDto;
import com.jobPrize.dto.memToCom.application.ApplicationSummaryForCompanyDto;
import com.jobPrize.dto.memToCom.application.ApplicationSummaryForMemberDto;
import com.jobPrize.entity.company.JobPosting;
import com.jobPrize.entity.memToCom.Application;
import com.jobPrize.entity.member.Member;
import com.jobPrize.enumerate.EducationLevel;
import com.jobPrize.enumerate.UserType;
import com.jobPrize.repository.company.jobPosting.JobPostingRepository;
import com.jobPrize.repository.memToCom.application.ApplicationRepository;
import com.jobPrize.repository.memToCom.pass.PassRepository;
import com.jobPrize.repository.member.member.MemberRepository;
import com.jobPrize.util.AssertUtil;
import com.jobPrize.util.JsonUtil;
import com.jobPrize.util.MemToComUtil;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

	private final MemberRepository memberRepository;

	private final ApplicationRepository applicationRepository;

	private final JobPostingRepository jobPostingRepository;
	
	private final PassRepository passRepository;
	
	private final JsonUtil jsonUtil;
	
	private final MemToComUtil memToComUtil;

	private final AssertUtil assertUtil;

	@Override
	public void createApplication(Long id, UserType userType, ApplicationCreateDto applicationCreateDto) {
		
		assertUtil.assertUserType(userType, UserType.일반회원, "지원서 등록");
		
		Member member = memberRepository.findByIdAndDeletedDateIsNull(id)
				.orElseThrow(() -> new CustomEntityNotFoundException("회원"));
		
		JobPosting jobPosting = jobPostingRepository.findById(applicationCreateDto.getJobPostingId())
				.orElseThrow(() -> new CustomEntityNotFoundException("공고"));
		
		Long careerDescriptionId = applicationCreateDto.getCareerDescriptionId();
		Long coverLetterId = applicationCreateDto.getCoverLetterId();

		String resumeJson =jsonUtil.getResumeJsonByMemberId(id);
		String careerDescriptionJson =jsonUtil.getCareerDescriptionJsonByCareerDescriptionId(id, careerDescriptionId);
		String coverLetterJson =jsonUtil.getCoverLetterJsonByCoverLetterId(id, coverLetterId);
		
		
		Application application = Application.of(member, jobPosting, resumeJson, careerDescriptionJson, coverLetterJson);
		
		applicationRepository.save(application);

	}

    
	@Override
	@Transactional(readOnly = true)
	public Page<ApplicationSummaryForMemberDto> readApplicationForMemberPage(Long id, Pageable pageable) {
		Page<Application> applications = applicationRepository.findAllByMemberId(id, pageable);

		List<ApplicationSummaryForMemberDto> applicationSummaryDtos = new ArrayList<>();
		for(Application application : applications) {
			ApplicationSummaryForMemberDto applicationSummaryDto = ApplicationSummaryForMemberDto.from(application);
			applicationSummaryDtos.add(applicationSummaryDto);
		}
		
		
		return new PageImpl<ApplicationSummaryForMemberDto>(applicationSummaryDtos,pageable,applications.getTotalElements());
	}
	

	@Override
	@Transactional(readOnly = true)
	public Page<ApplicationSummaryForCompanyDto> readApplicationForCompanyPage(Long id, Long jobPostingId, Pageable pageable) {

		
		Page<Application> applications = applicationRepository.findAllByJobPostingId(jobPostingId, pageable);
		
	    List<Application> applicationList = applications.getContent();

	    if (!applicationList.isEmpty()) {
	        assertUtil.assertId(id, applicationList.get(0), "지원서 조회");
	    }

		List<ApplicationSummaryForCompanyDto> applicationSummaryForCompanyDtos = new ArrayList<>();

		for(Application application : applications) {
			
			boolean hasCareer = memToComUtil.hasCareer(application);
			EducationLevel latestEducationLevel = memToComUtil.latestEducationLevel(application);
			boolean isPassed = passRepository.existsByApplicationId(application.getId());
			
			ApplicationSummaryForCompanyDto applicationSummaryForCompanyDto = ApplicationSummaryForCompanyDto.of(application, hasCareer, latestEducationLevel, isPassed);

			
			applicationSummaryForCompanyDtos.add(applicationSummaryForCompanyDto);
		}
		
		return new PageImpl<ApplicationSummaryForCompanyDto>(applicationSummaryForCompanyDtos, pageable, applications.getTotalElements() );
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<ApplicationSummaryForCompanyDto> readPassedApplicationPage(Long id, Long jobPostingId, Pageable pageable) { 
		
	    Page<Application> applications = applicationRepository.findPassedByJobPostingId(jobPostingId, pageable); 
	    
	    List<Application> applicationList = applications.getContent();

	    if (!applicationList.isEmpty()) {
	        assertUtil.assertId(id, applicationList.get(0), "지원서 조회");
	    }

	    List<ApplicationSummaryForCompanyDto> applicationSummaryForCompanyDtos = new ArrayList<>();

	    for (Application application : applications) {
	        boolean hasCareer = memToComUtil.hasCareer(application);
	        EducationLevel latestEducationLevel = memToComUtil.latestEducationLevel(application);

	        ApplicationSummaryForCompanyDto applicationSummaryForCompanyDto = ApplicationSummaryForCompanyDto.of(application, hasCareer, latestEducationLevel, true);
	        applicationSummaryForCompanyDtos.add(applicationSummaryForCompanyDto);

	    }

	    return new PageImpl<>(applicationSummaryForCompanyDtos, pageable, applications.getTotalElements());
	}

	@Override
	@Transactional(readOnly = true)
	public ApplicationResponseDto readApplication(Long id, UserType userType, Long applicationId) {
		
		assertUtil.assertUserType(userType, UserType.일반회원, UserType.기업회원, "조회");
		
		Application application = applicationRepository.findByApplicationId(applicationId)
			.orElseThrow(() -> new CustomEntityNotFoundException("지원서"));

		
		if(UserType.기업회원.equals(userType)) {
        	if(!application.getJobPosting().getCompany().getId().equals(id)) {
        		throw new CustomOwnerMismatchException("Application", "조회");
        	}
        }
        else if(UserType.일반회원.equals(userType)) {
        	if(!application.getMember().getId().equals(id)) {
        		throw new CustomOwnerMismatchException("Application", "조회");
        	}
        }
		
		return ApplicationResponseDto.from(application);
	}
	
}
