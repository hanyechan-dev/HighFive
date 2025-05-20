package com.jobPrize.memberService.service.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.entity.common.UserType;
import com.jobPrize.entity.company.JobPosting;
import com.jobPrize.entity.memToCom.Application;
import com.jobPrize.entity.member.Member;
import com.jobPrize.memberService.dto.application.ApplicationCreateDto;
import com.jobPrize.memberService.dto.application.ApplicationResponseDto;
import com.jobPrize.memberService.dto.application.ApplicationSummaryDto;
import com.jobPrize.memberService.service.document.DocumentToJson;
import com.jobPrize.repository.common.jobPosting.JobPostingRepository;
import com.jobPrize.repository.memToCom.application.ApplicationRepository;
import com.jobPrize.repository.member.member.MemberRepository;

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

	@Override
	public Page<ApplicationSummaryDto> getApplicationPage(Long id, Pageable pageable) {
		Page<Application> applications = applicationRepository.findAllByMemberId(id, pageable);

		List<ApplicationSummaryDto> applicationSummaryDtos = new ArrayList<>();
		for(Application application : applications) {
			ApplicationSummaryDto applicationSummaryDto = ApplicationSummaryDto.from(application);
			applicationSummaryDtos.add(applicationSummaryDto);
		}
		
		
		return new PageImpl<ApplicationSummaryDto>(applicationSummaryDtos,pageable,applications.getTotalElements());
	}

	@Override
	public ApplicationResponseDto getApplication(Long id, Long applicationId) {
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
