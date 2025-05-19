package com.jobPrize.memberService.service.request;

import java.time.LocalDate;
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
import com.jobPrize.entity.consultant.AiConsulting;
import com.jobPrize.entity.consultant.AiConsultingContent;
import com.jobPrize.entity.consultant.CommonEnum;
import com.jobPrize.entity.memToCom.Application;
import com.jobPrize.entity.memToCon.Request;
import com.jobPrize.entity.member.Career;
import com.jobPrize.entity.member.CareerDescription;
import com.jobPrize.entity.member.Certification;
import com.jobPrize.entity.member.CoverLetter;
import com.jobPrize.entity.member.Education;
import com.jobPrize.entity.member.LanguageTest;
import com.jobPrize.entity.member.Member;
import com.jobPrize.jwt.TokenProvider;
import com.jobPrize.memberService.dto.career.CareerResponseDto;
import com.jobPrize.memberService.dto.careerDescription.CareerDescriptionResponseDto;
import com.jobPrize.memberService.dto.certification.CertificationResponseDto;
import com.jobPrize.memberService.dto.coverLetter.CoverLetterResponseDto;
import com.jobPrize.memberService.dto.education.EducationResponseDto;
import com.jobPrize.memberService.dto.languageTest.LanguageTestResponseDto;
import com.jobPrize.memberService.dto.request.AiConsultingContentResponseDto;
import com.jobPrize.memberService.dto.request.AiConsultingResponseDto;
import com.jobPrize.memberService.dto.request.RequestCreateDto;
import com.jobPrize.memberService.dto.request.RequestResponseDto;
import com.jobPrize.memberService.dto.request.RequestSummaryDto;
import com.jobPrize.repository.common.jobPosting.JobPostingRepository;
import com.jobPrize.repository.memToCom.application.ApplicationRepository;
import com.jobPrize.repository.memToCon.request.RequestRepository;
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
public class RequestServiceImpl implements RequestService {

	private final MemberRepository memberRepository;

	private final ApplicationRepository applicationRepository;

	private final EducationRepository educationRepository;

	private final CareerRepository careerRepository;

	private final CertificationRepository certificationRepository;

	private final LanguageTestRepository languageTestRepository;

	private final CoverLetterRepository coverLetterRepository;

	private final CareerDescriptionRepository careerDescriptionRepository;
	
	private final JobPostingRepository jobPostingRepository;
	
	private final RequestRepository requestRepository;

	private final ObjectMapper objectMapper;

	private final TokenProvider tokenProvider;
	
	@Override
	public Page<RequestSummaryDto> getListFeedbackRequest(String token, Pageable pageable) {
		Long id = tokenProvider.getIdFromToken(token);
		Page<Request> requests = requestRepository.findAllByMemberIdAndType(id,CommonEnum.ConsultingType.피드백 ,pageable);
		
		List<RequestSummaryDto> requestSummaryDtos = new ArrayList<>();
		for(Request request : requests) {

			LocalDate date = getPriorityDate(request);
	
			RequestSummaryDto requestSummaryDto = RequestSummaryDto.of(request,date);
			requestSummaryDtos.add(requestSummaryDto);
		}
		
		
		return new PageImpl<RequestSummaryDto>(requestSummaryDtos,pageable, requests.getTotalElements());
	}

	@Override
	public Page<RequestSummaryDto> getListEditRequest(String token, Pageable pageable) {
		Long id = tokenProvider.getIdFromToken(token);
		Page<Request> requests = requestRepository.findAllByMemberIdAndType(id,CommonEnum.ConsultingType.첨삭 ,pageable);
		
		List<RequestSummaryDto> requestSummaryDtos = new ArrayList<>();
		for(Request request : requests) {

			LocalDate date = getPriorityDate(request);
	
			RequestSummaryDto requestSummaryDto = RequestSummaryDto.of(request,date);
			requestSummaryDtos.add(requestSummaryDto);
		}
		
		
		return new PageImpl<RequestSummaryDto>(requestSummaryDtos,pageable, requests.getTotalElements());
	}

	@Override
	public Map<String, Object> getRequest(String token, Long requestId) {
		Long id = tokenProvider.getIdFromToken(token);
		Request request = requestRepository.findWithAiConsultingByRequestId(requestId)
			.orElseThrow(() -> new EntityNotFoundException("존재하지 않는 컨설팅 요청입니다."));
		
		if(!request.getMember().getId().equals(id)) {
			throw new AccessDeniedException("요청한 회원만 조회할 수 있습니다.");
		}
		
		AiConsulting aiConsulting = request.getAiConsulting();
		List<AiConsultingContent> aiConsultingContents = aiConsulting.getAiConsultingContents();
		RequestResponseDto requestResponseDto = RequestResponseDto.from(request);
		AiConsultingResponseDto aiConsultingResponseDto = AiConsultingResponseDto.from(aiConsulting);
		List<AiConsultingContentResponseDto> aiConsultingContentResponseDtos = new ArrayList<>();
		for(AiConsultingContent aiConsultingContent : aiConsultingContents ) {
			AiConsultingContentResponseDto aiConsultingContentResponseDto = AiConsultingContentResponseDto.from(aiConsultingContent);
			aiConsultingContentResponseDtos.add(aiConsultingContentResponseDto);
		}
		
		Map<String, Object> totalRequestResponseDto = new HashMap<>();
		totalRequestResponseDto.put("requestResponseDto", requestResponseDto);
		totalRequestResponseDto.put("aiConsultingResponseDto", aiConsultingResponseDto);
		totalRequestResponseDto.put("aiConsultingContentResponseDtos", aiConsultingContentResponseDtos);
		
		
		
		return totalRequestResponseDto;
	}

	@Override
	public void createRequest(String token, RequestCreateDto requestCreateDto) {
		if (!tokenProvider.getUserTypeFromToken(token).equals(UserType.일반회원)) {
			throw new AccessDeniedException("일반회원만 요청할 수 있습니다.");
		}
		
		
		Long id = tokenProvider.getIdFromToken(token);

		Member member = memberRepository.findByIdAndDeletedDateIsNull(id)
				.orElseThrow(() -> new EntityNotFoundException("존재하지 않는 회원입니다."));

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
				.findWithCoverLetterContentsByCoverLetterId(requestCreateDto.getCoverLetterId())
				.orElseThrow(() -> new IllegalStateException("존재하지 않는 자기소개서입니다."));
		CoverLetterResponseDto coverLetterResponseDto = CoverLetterResponseDto.from(coverLetter);

		CareerDescription careerDescription = careerDescriptionRepository
				.findWithCareerDescriptionContentsByCareerDescriptionId(requestCreateDto.getCareerDescriptionId())
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

		Request request = Request.builder()
				.member(member)
				.targetJob(requestCreateDto.getTargetJob())
				.targetCompanyName(requestCreateDto.getTargetCompanyName())
				.resumeJson(resumeJson)
				.careerDescriptionJson(careerDescriptionJson)
				.coverLetterJson(coverLetterJson)
				.build();
		requestRepository.save(request);
		
	}
	
	
	
	private LocalDate getPriorityDate(Request request) {
		LocalDate date = null;
		
		if(request.getAiConsulting()!= null) {
			if(request.getAiConsulting().getConsultantConsulting()!=null) {
				if(request.getAiConsulting().getConsultantConsulting().getCompletedDate()!=null) {
					date = request.getAiConsulting().getConsultantConsulting().getCompletedDate();
				}
				else {
					date = request.getAiConsulting().getConsultantConsulting().getCreatedDate();
				}
			}
			else if(request.getAiConsulting().getRequestedDate()!=null) {
				date = request.getAiConsulting().getRequestedDate();
			}
			
			else{
				date = request.getCreatedDate();
			}
		}
		else{
			date = request.getCreatedDate();
		}
		
		return date;
	}
	








}
