package com.jobPrize.memberService.service.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobPrize.entity.common.UserType;
import com.jobPrize.entity.company.JobPosting;
import com.jobPrize.entity.memToCom.Application;
import com.jobPrize.entity.memToCom.Proposal;
import com.jobPrize.entity.member.Career;
import com.jobPrize.entity.member.CareerDescription;
import com.jobPrize.entity.member.Certification;
import com.jobPrize.entity.member.CoverLetter;
import com.jobPrize.entity.member.Education;
import com.jobPrize.entity.member.LanguageTest;
import com.jobPrize.entity.member.Member;
import com.jobPrize.jwt.TokenProvider;
import com.jobPrize.memberService.dto.application.ApplicationCreateDto;
import com.jobPrize.memberService.dto.application.ApplicationResponseDto;
import com.jobPrize.memberService.dto.application.ApplicationSummaryDto;
import com.jobPrize.memberService.dto.career.CareerResponseDto;
import com.jobPrize.memberService.dto.careerDescription.CareerDescriptionResponseDto;
import com.jobPrize.memberService.dto.certification.CertificationResponseDto;
import com.jobPrize.memberService.dto.coverLetter.CoverLetterResponseDto;
import com.jobPrize.memberService.dto.education.EducationResponseDto;
import com.jobPrize.memberService.dto.languageTest.LanguageTestResponseDto;
import com.jobPrize.memberService.dto.proposal.ProposalSummaryDto;
import com.jobPrize.repository.common.jobPosting.JobPostingRepository;
import com.jobPrize.repository.memToCom.application.ApplicationRepository;
import com.jobPrize.repository.member.career.CareerRepository;
import com.jobPrize.repository.member.careerDescription.CareerDescriptionRepository;
import com.jobPrize.repository.member.certification.CertificationRepository;
import com.jobPrize.repository.member.coverLetter.CoverLetterRepository;
import com.jobPrize.repository.member.education.EducationRepository;
import com.jobPrize.repository.member.languageTest.LanguageTestRepository;
import com.jobPrize.repository.member.member.MemberRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

	private final MemberRepository memberRepository;

	private final ApplicationRepository applicationRepository;

	private final EducationRepository educationRepository;

	private final CareerRepository careerRepository;

	private final CertificationRepository certificationRepository;

	private final LanguageTestRepository languageTestRepository;

	private final CoverLetterRepository coverLetterRepository;

	private final CareerDescriptionRepository careerDescriptionRepository;
	
	private final JobPostingRepository jobPostingRepository;

	private final ObjectMapper objectMapper;

	private final TokenProvider tokenProvider;

	@Override
	public Page<ApplicationSummaryDto> getListApplication(String token, Pageable pageable) {
		Long id = tokenProvider.getIdFromToken(token);
		Page<Application> applications = applicationRepository.findAllByMemberId(id, pageable);

		List<ApplicationSummaryDto> applicationSummaryDtos = new ArrayList<>();
		for(Application application : applications) {
			ApplicationSummaryDto applicationSummaryDto = ApplicationSummaryDto.from(application);
			applicationSummaryDtos.add(applicationSummaryDto);
		}
		
		
		return new PageImpl<ApplicationSummaryDto>(applicationSummaryDtos,pageable,applications.getTotalElements());
	}

	@Override
	public ApplicationResponseDto getApplication(String token, Long applicationId) {
		Long id = tokenProvider.getIdFromToken(token);
		Application application = applicationRepository.findByApplicationId(applicationId)
			.orElseThrow(() -> new EntityNotFoundException("존재하지 않는 지원서입니다."));
		
		if(!application.getMember().getId().equals(id)) {
			throw new AccessDeniedException("지원한 회원만 조회할 수 있습니다.");
		}
		
		
		return ApplicationResponseDto.from(application);
	}

	@Override
	public void createApplication(String token, ApplicationCreateDto applicationCreateDto) {
		if (!tokenProvider.getUserTypeFromToken(token).equals(UserType.일반회원)) {
			throw new AccessDeniedException("일반회원만 지원할 수 있습니다.");
		}
		
		
		Long id = tokenProvider.getIdFromToken(token);

		Member member = memberRepository.findByIdAndDeletedDateIsNull(id)
				.orElseThrow(() -> new EntityNotFoundException("존재하지 않는 회원입니다."));
		
		JobPosting jobPosting = jobPostingRepository.findById(applicationCreateDto.getJobPostingId())
				.orElseThrow(() -> new EntityNotFoundException("존재하지 않는 공고입니다."));

		List<Education> educations = educationRepository.findAllByMemberId(id);
		List<EducationResponseDto> educationResponseDtos = new ArrayList<>();
		
		
		for (Education education : educations) {
			EducationResponseDto educationResponseDto = EducationResponseDto.from(education);
			educationResponseDtos.add(educationResponseDto);
		}

		List<Career> careers = careerRepository.findAllByMemberId(id);
		List<CareerResponseDto> careerResponseDtos = new ArrayList<>();
		
		
		for (Career career : careers) {
			CareerResponseDto careerResponseDto = CareerResponseDto.from(career);
			careerResponseDtos.add(careerResponseDto);
		}
		List<Certification> certifications = certificationRepository.findAllByMemberId(id);
		List<CertificationResponseDto> certificationResponseDtos = new ArrayList<>();
		
		
		for (Certification certification : certifications) {
			CertificationResponseDto certificationResponseDto = CertificationResponseDto.from(certification);
			certificationResponseDtos.add(certificationResponseDto);
		}
		List<LanguageTest> languageTests = languageTestRepository.findAllByMemberId(id);
		List<LanguageTestResponseDto> languageTestResponseDtos = new ArrayList<>();
		
		
		for (LanguageTest languageTest : languageTests) {
			LanguageTestResponseDto languageTestResponseDto = LanguageTestResponseDto.from(languageTest);
			languageTestResponseDtos.add(languageTestResponseDto);
		}
		
		
		CoverLetter coverLetter = coverLetterRepository
				.findWithCoverLetterContentsByCoverLetterId(applicationCreateDto.getCoverLetterId())
				.orElseThrow(() -> new IllegalStateException("존재하지 않는 자기소개서입니다."));
		CoverLetterResponseDto coverLetterResponseDto = CoverLetterResponseDto.from(coverLetter);

		CareerDescription careerDescription = careerDescriptionRepository
				.findWithCareerDescriptionContentsByCareerDescriptionId(applicationCreateDto.getCareerDescriptionId())
				.orElseThrow(() -> new IllegalStateException("존재하지 않는 경력기술서입니다."));
		CareerDescriptionResponseDto careerDescriptionResponseDto = CareerDescriptionResponseDto
				.from(careerDescription);

		Map<String, Object> resumeMap = new HashMap<>();
		resumeMap.put("educationDtos", educationResponseDtos);
		resumeMap.put("careerDtos", careerResponseDtos);
		resumeMap.put("certificationDtos", certificationResponseDtos);
		resumeMap.put("languageTestDtos", languageTestResponseDtos);

		Map<String, Object> coverLetterMap = new HashMap<>();
		coverLetterMap.put("coverLetterResponseDto", coverLetterResponseDto);

		Map<String, Object> careerDescriptionMap = new HashMap<>();
		careerDescriptionMap.put("careerDescriptionResponseDto", careerDescriptionResponseDto);
		
		String resumeJson = null;
		String coverLetterJson = null;
		String careerDescriptionJson = null;
		
		
		try {
			resumeJson = objectMapper.writeValueAsString(resumeMap);
			coverLetterJson = objectMapper.writeValueAsString(coverLetterMap);
			careerDescriptionJson = objectMapper.writeValueAsString(careerDescriptionMap);
		} catch (JsonProcessingException e) {
			throw new IllegalStateException("이력서 JSON 변환 중 오류 발생", e);
		}

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
