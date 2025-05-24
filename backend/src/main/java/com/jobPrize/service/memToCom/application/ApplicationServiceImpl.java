package com.jobPrize.service.memToCom.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.dto.memToCom.application.ApplicationCreateDto;
import com.jobPrize.dto.memToCom.application.ApplicationResponseDto;
import com.jobPrize.dto.memToCom.application.ApplicationSummaryForCompanyDto;
import com.jobPrize.dto.memToCom.application.ApplicationSummaryForMemberDto;
import com.jobPrize.entity.common.UserType;
import com.jobPrize.entity.company.JobPosting;
import com.jobPrize.entity.memToCom.Application;
import com.jobPrize.entity.memToCom.EducationLevel;
import com.jobPrize.entity.member.Member;
import com.jobPrize.repository.company.jobPosting.JobPostingRepository;
import com.jobPrize.repository.memToCom.application.ApplicationRepository;
import com.jobPrize.repository.member.member.MemberRepository;
import com.jobPrize.service.memToCom.util.MemToComUtil;
import com.jobPrize.service.member.document.DocumentToJson;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

	private final MemberRepository memberRepository;

	private final ApplicationRepository applicationRepository;

	private final JobPostingRepository jobPostingRepository;
	
	private final DocumentToJson documentToJson;
	
	private final MemToComUtil memToComUtil;

    
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
	public Page<ApplicationSummaryForCompanyDto> readApplicationForCompanyPage(Long jobPostingId, Pageable pageable) {
		Page<Application> applications = applicationRepository.findAllByJobPostingId(jobPostingId, pageable);

		
		List<ApplicationSummaryForCompanyDto> applicationSummaryForCompanyDtos = new ArrayList<>();
		
		for(Application application : applications) {

			boolean hasCareer = memToComUtil.hasCareer(application);
			EducationLevel latestEducationLevel = memToComUtil.latestEducationLevel(application);
			boolean isInterested = memToComUtil.isInterested(application);

			ApplicationSummaryForCompanyDto applicationSummaryForCompanyDto = ApplicationSummaryForCompanyDto.of(application, hasCareer, latestEducationLevel, isInterested);

			
			applicationSummaryForCompanyDtos.add(applicationSummaryForCompanyDto);
		}
		
		return new PageImpl<ApplicationSummaryForCompanyDto>(applicationSummaryForCompanyDtos, pageable, applications.getTotalElements() );
	}

	@Override
	@Transactional(readOnly = true)
	public ApplicationResponseDto readApplication(Long id, Long applicationId) {
		Application application = applicationRepository.findByApplicationId(applicationId)
			.orElseThrow(() -> new EntityNotFoundException("존재하지 않는 지원서입니다."));
		
		if(!application.getMember().getId().equals(id)) {
			throw new AccessDeniedException("지원한 회원만 조회할 수 있습니다.");
		}
		
		
		return ApplicationResponseDto.from(application);
	}

	@Override
	public void createApplication(Long id, UserType userType, ApplicationCreateDto applicationCreateDto) {
		
		if (userType.equals(UserType.일반회원)) {
			throw new AccessDeniedException("일반회원만 지원할 수 있습니다.");
		}

		Member member = memberRepository.findByIdAndDeletedDateIsNull(id)
				.orElseThrow(() -> new EntityNotFoundException("존재하지 않는 회원입니다."));
		
		JobPosting jobPosting = jobPostingRepository.findById(applicationCreateDto.getJobPostingId())
				.orElseThrow(() -> new EntityNotFoundException("존재하지 않는 공고입니다."));
		
		Long careerDescriptionId = applicationCreateDto.getCareerDescriptionId();
		Long coverLetterId = applicationCreateDto.getCoverLetterId();

		String resumeJson =documentToJson.getResumeJsonByMemberId(id);
		String careerDescriptionJson =documentToJson.getCareerDescriptionJsonByCareerDescriptionId(id, careerDescriptionId);
		String coverLetterJson =documentToJson.getCoverLetterJsonByCoverLetterId(id, coverLetterId);
		
		
		Application application = Application.builder()
				.member(member)
				.jobPosting(jobPosting)
				.resumeJson(resumeJson)
				.careerDescriptionJson(careerDescriptionJson)
				.coverLetterJson(coverLetterJson)
				.build();
		
		applicationRepository.save(application);

	}
	
	
	
	
	
	
	
	
	
	
	
	
	



}
