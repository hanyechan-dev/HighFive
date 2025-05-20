package com.jobPrize.memberService.service.document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobPrize.entity.member.Career;
import com.jobPrize.entity.member.CareerDescription;
import com.jobPrize.entity.member.CareerDescriptionContent;
import com.jobPrize.entity.member.Certification;
import com.jobPrize.entity.member.CoverLetter;
import com.jobPrize.entity.member.CoverLetterContent;
import com.jobPrize.entity.member.Education;
import com.jobPrize.entity.member.LanguageTest;
import com.jobPrize.memberService.dto.career.CareerResponseDto;
import com.jobPrize.memberService.dto.careerDescription.CareerDescriptionContentResponseDto;
import com.jobPrize.memberService.dto.careerDescription.CareerDescriptionResponseDto;
import com.jobPrize.memberService.dto.certification.CertificationResponseDto;
import com.jobPrize.memberService.dto.coverLetter.CoverLetterContentResponseDto;
import com.jobPrize.memberService.dto.coverLetter.CoverLetterResponseDto;
import com.jobPrize.memberService.dto.education.EducationResponseDto;
import com.jobPrize.memberService.dto.languageTest.LanguageTestResponseDto;
import com.jobPrize.repository.member.career.CareerRepository;
import com.jobPrize.repository.member.careerDescription.CareerDescriptionRepository;
import com.jobPrize.repository.member.certification.CertificationRepository;
import com.jobPrize.repository.member.coverLetter.CoverLetterRepository;
import com.jobPrize.repository.member.education.EducationRepository;
import com.jobPrize.repository.member.languageTest.LanguageTestRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DocumentToJson {

	private final EducationRepository educationRepository;
	private final CareerRepository careerRepository;
	private final CertificationRepository certificationRepository;
	private final LanguageTestRepository languageTestRepository;
	private final CoverLetterRepository coverLetterRepository;
	private final CareerDescriptionRepository careerDescriptionRepository;
	private final ObjectMapper objectMapper;

	public String getResumeJsonByMemberId(Long id) {
		// 교육, 경력, 자격증, 어학 → DTO → Map → JSON
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
		
		Map<String, Object> resumeMap = new HashMap<>();
		resumeMap.put("educationDtos", educationResponseDtos);
		resumeMap.put("careerDtos", careerResponseDtos);
		resumeMap.put("certificationDtos", certificationResponseDtos);
		resumeMap.put("languageTestDtos", languageTestResponseDtos);
		
		String resumeJson = null;
		try {
			resumeJson = objectMapper.writeValueAsString(resumeMap);
		} catch (JsonProcessingException e) {
			throw new IllegalStateException("이력서 JSON 변환 중 오류 발생", e);
		}
		
		return resumeJson;
	}

	public String getCoverLetterJsonByCoverLetterId(Long id, Long coverLetterId) {
		// coverLetter 소유자 체크 + contents 포함 → JSON
		CoverLetter coverLetter = coverLetterRepository
				.findWithCoverLetterContentsByCoverLetterId(coverLetterId)
				.orElseThrow(() -> new EntityNotFoundException("존재하지 않는 자기소개서입니다."));
		
		if(!coverLetter.getMember().getId().equals(id)) {
			throw new AccessDeniedException("해당 자기소개서를 선택할 수 없습니다.");
		}
		
		List<CoverLetterContent> coverLetterContents = coverLetter.getCoverLetterContents();
		List<CoverLetterContentResponseDto> coverLetterContentResponseDtos = new ArrayList<>();
		
		for(CoverLetterContent coverLetterContent : coverLetterContents) {
			coverLetterContentResponseDtos.add(CoverLetterContentResponseDto.from(coverLetterContent));
		}
		
		CoverLetterResponseDto coverLetterResponseDto = CoverLetterResponseDto.of(coverLetter, coverLetterContentResponseDtos);
		
		Map<String, Object> coverLetterMap = new HashMap<>();
		coverLetterMap.put("coverLetterResponseDto", coverLetterResponseDto);
		String coverLetterJson = null;
		
		try {
			coverLetterJson = objectMapper.writeValueAsString(coverLetterMap);
		} catch (JsonProcessingException e) {
			throw new IllegalStateException("자기소개서 JSON 변환 중 오류 발생", e);
		}
		
		return coverLetterJson;
	}

	public String getCareerDescriptionJsonByCareerDescriptionId(Long id, Long careerDescriptionId) {
		// careerDescription 소유자 체크 + contents 포함 → JSON
		CareerDescription careerDescription = careerDescriptionRepository
				.findWithCareerDescriptionContentsByCareerDescriptionId(careerDescriptionId)
				.orElseThrow(() -> new IllegalStateException("존재하지 않는 경력기술서입니다."));
		
		if(!careerDescription.getMember().getId().equals(id)) {
			throw new AccessDeniedException("해당 경력기술서를 선택할 수 없습니다.");
		}
		
		List<CareerDescriptionContent> careerDescriptionContents = careerDescription.getCareerDescriptionContents();
		List<CareerDescriptionContentResponseDto> careerDescriptionContentResponseDtos = new ArrayList<>();
		
		for(CareerDescriptionContent careerDescriptionContent : careerDescriptionContents) {
			careerDescriptionContentResponseDtos.add(CareerDescriptionContentResponseDto.from(careerDescriptionContent));
		}
		
		
		CareerDescriptionResponseDto careerDescriptionResponseDto = CareerDescriptionResponseDto.of(careerDescription, careerDescriptionContentResponseDtos);

		Map<String, Object> careerDescriptionMap = new HashMap<>();
		careerDescriptionMap.put("careerDescriptionResponseDto", careerDescriptionResponseDto);
		String careerDescriptionJson = null;
		
		
		try {
			careerDescriptionJson = objectMapper.writeValueAsString(careerDescriptionMap);
		} catch (JsonProcessingException e) {
			throw new IllegalStateException("경력기술서 JSON 변환 중 오류 발생", e);
		}
		
		return careerDescriptionJson;
	}
}