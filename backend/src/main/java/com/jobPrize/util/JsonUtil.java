package com.jobPrize.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobPrize.customException.CustomEntityNotFoundException;
import com.jobPrize.dto.member.career.CareerResponseDto;
import com.jobPrize.dto.member.careerDescription.CareerDescriptionContentResponseDto;
import com.jobPrize.dto.member.careerDescription.CareerDescriptionResponseDto;
import com.jobPrize.dto.member.certification.CertificationResponseDto;
import com.jobPrize.dto.member.coverLetter.CoverLetterContentResponseDto;
import com.jobPrize.dto.member.coverLetter.CoverLetterResponseDto;
import com.jobPrize.dto.member.education.EducationResponseDto;
import com.jobPrize.dto.member.languageTest.LanguageTestResponseDto;
import com.jobPrize.entity.member.Career;
import com.jobPrize.entity.member.CareerDescription;
import com.jobPrize.entity.member.CareerDescriptionContent;
import com.jobPrize.entity.member.Certification;
import com.jobPrize.entity.member.CoverLetter;
import com.jobPrize.entity.member.CoverLetterContent;
import com.jobPrize.entity.member.Education;
import com.jobPrize.entity.member.LanguageTest;
import com.jobPrize.repository.member.career.CareerRepository;
import com.jobPrize.repository.member.careerDescription.CareerDescriptionRepository;
import com.jobPrize.repository.member.certification.CertificationRepository;
import com.jobPrize.repository.member.coverLetter.CoverLetterRepository;
import com.jobPrize.repository.member.education.EducationRepository;
import com.jobPrize.repository.member.languageTest.LanguageTestRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JsonUtil {

	private final EducationRepository educationRepository;
	private final CareerRepository careerRepository;
	private final CertificationRepository certificationRepository;
	private final LanguageTestRepository languageTestRepository;
	private final CoverLetterRepository coverLetterRepository;
	private final CareerDescriptionRepository careerDescriptionRepository;
	private final ObjectMapper objectMapper;
	private final AssertUtil assertUtil;

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
		resumeMap.put("educationResponseDtos", educationResponseDtos);
		resumeMap.put("careerResponseDtos", careerResponseDtos);
		resumeMap.put("certificationResponseDtos", certificationResponseDtos);
		resumeMap.put("languageTestResponseDtos", languageTestResponseDtos);
		
		String resumeJson = null;
		try {
			resumeJson = objectMapper.writeValueAsString(resumeMap);
		} catch (JsonProcessingException e) {
			throw new IllegalStateException("이력서 JSON 변환 중 오류 발생", e);
		}
		
		return resumeJson;
	}

	public String getCoverLetterJsonByCoverLetterId(Long id, Long coverLetterId) {

		String entityName = "자기소개서";
		String actiom = "선택";
		// coverLetter 소유자 체크 + contents 포함 → JSON
		CoverLetter coverLetter = coverLetterRepository
				.findWithCoverLetterContentsByCoverLetterId(coverLetterId)
				.orElseThrow(() -> new CustomEntityNotFoundException(entityName));
		
		Long ownerId = coverLetterRepository.findMemberIdByCoverLetterId(coverLetterId)
				.orElseThrow(() -> new CustomEntityNotFoundException("소유자"));

		assertUtil.assertId(id, ownerId, entityName, actiom);
		
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
		String entityName = "경력기술서";
		String action = "선택";
		// careerDescription 소유자 체크 + contents 포함 → JSON
		Map<String, Object> careerDescriptionMap = new HashMap<>();
		
		if (careerDescriptionId != null && careerDescriptionId != -1L) {
			CareerDescription careerDescription = careerDescriptionRepository
					.findWithCareerDescriptionContentsByCareerDescriptionId(careerDescriptionId)
					.orElseThrow(() -> new CustomEntityNotFoundException(entityName));
			
			Long ownerId = careerDescriptionRepository.findMemberIdByCareerDescriptionId(careerDescriptionId)
					.orElseThrow(() -> new CustomEntityNotFoundException("소유자"));

			assertUtil.assertId(id, ownerId, entityName, action);
			
			List<CareerDescriptionContent> careerDescriptionContents = careerDescription.getCareerDescriptionContents();
			List<CareerDescriptionContentResponseDto> careerDescriptionContentResponseDtos = new ArrayList<>();
			
			for(CareerDescriptionContent careerDescriptionContent : careerDescriptionContents) {
				careerDescriptionContentResponseDtos.add(CareerDescriptionContentResponseDto.from(careerDescriptionContent));
			}
			
			
			CareerDescriptionResponseDto careerDescriptionResponseDto = CareerDescriptionResponseDto.of(careerDescription, careerDescriptionContentResponseDtos);
			
			careerDescriptionMap.put("careerDescriptionResponseDto", careerDescriptionResponseDto);
		}
		
		String careerDescriptionJson = null;
		
		
		try {
			careerDescriptionJson = objectMapper.writeValueAsString(careerDescriptionMap);
		} catch (JsonProcessingException e) {
			throw new IllegalStateException("경력기술서 JSON 변환 중 오류 발생", e);
		}
		
		return careerDescriptionJson;
	}
	
	public Map<String, List<?>> parseResumeMapFromResumeJson(String resumeJson) {
	    try {
	        JsonNode root = objectMapper.readTree(resumeJson);

	        List<EducationResponseDto> educationResponseDtos = objectMapper.readValue(
	            root.get("educationResponseDtos").toString(),
	            new TypeReference<List<EducationResponseDto>>() {}
	        );

	        List<CareerResponseDto> careerResponseDtos = objectMapper.readValue(
	            root.get("careerResponseDtos").toString(),
	            new TypeReference<List<CareerResponseDto>>() {}
	        );

	        List<CertificationResponseDto> certificationResponseDtos = objectMapper.readValue(
	            root.get("certificationResponseDtos").toString(),
	            new TypeReference<List<CertificationResponseDto>>() {}
	        );

	        List<LanguageTestResponseDto> languageTestResponseDtos = objectMapper.readValue(
	            root.get("languageTestResponseDtos").toString(),
	            new TypeReference<List<LanguageTestResponseDto>>() {}
	        );

	        Map<String, List<?>> resumeMap = new HashMap<>();
	        resumeMap.put("educationResponseDtos", educationResponseDtos);
	        resumeMap.put("careerResponseDtos", careerResponseDtos);
	        resumeMap.put("certificationResponseDtos", certificationResponseDtos);
	        resumeMap.put("languageTestResponseDtos", languageTestResponseDtos);

	        return resumeMap;

	    } catch (Exception e) {
	        throw new IllegalStateException("이력서 JSON 파싱 실패", e);
	    }
	}

	public CoverLetterResponseDto parseCoverLetterJson(String json) {
	    try {
	        JsonNode root = objectMapper.readTree(json);
	        return objectMapper.treeToValue(root.get("coverLetterResponseDto"), CoverLetterResponseDto.class);
	    } catch (JsonProcessingException e) {
	        throw new IllegalStateException("자기소개서 JSON 파싱 실패", e);
	    }
	}

	public CareerDescriptionResponseDto parseCareerDescriptionJson(String json) {
	    try {
	        JsonNode root = objectMapper.readTree(json);
	        return objectMapper.treeToValue(root.get("careerDescriptionResponseDto"), CareerDescriptionResponseDto.class);
	    } catch (JsonProcessingException e) {
	        throw new IllegalStateException("경력기술서 JSON 파싱 실패", e);
	    }
	}

	
	
}