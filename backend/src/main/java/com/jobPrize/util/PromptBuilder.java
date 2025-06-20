package com.jobPrize.util;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.jobPrize.dto.memToCon.request.RequestResponseDto;
import com.jobPrize.dto.member.career.CareerResponseDto;
import com.jobPrize.dto.member.careerDescription.CareerDescriptionContentResponseDto;
import com.jobPrize.dto.member.careerDescription.CareerDescriptionResponseDto;
import com.jobPrize.dto.member.certification.CertificationResponseDto;
import com.jobPrize.dto.member.coverLetter.CoverLetterContentResponseDto;
import com.jobPrize.dto.member.coverLetter.CoverLetterResponseDto;
import com.jobPrize.dto.member.education.EducationResponseDto;
import com.jobPrize.dto.member.languageTest.LanguageTestResponseDto;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PromptBuilder {

	private final JsonUtil jsonUtil;

	public List<Map<String, String>> getFeedbackPrompt(RequestResponseDto requestResponseDto, String guidePrompt) {
		
		String inputString = getInputStringFromRequestResponseDto(requestResponseDto);
		
        Map<String, String> system = Map.of(
            "role", "system",
            "content", """ 
            		다음과 같은 JSON 형태로 출력해줘:
            		{
            		"피드백": [
            			{
            				"item": "성장과정",
            				"comment": "내용이 추상적입니다. 구체적인 사례를 추가하면 좋겠습니다."
            				"documentType" : "경력기술서"
            			},
            			{
            				"item": "입사 후 포부",
            				"comment": "회사에 기여하고 싶은 방향이 드러나지 않습니다."
            				"documentType" : "경력기술서"
            			}
            			]
            		}
            		
            		규칙:
            		- documentType은 각 블록 제목인 [경력기술서], [자기소개서]에 따라 명시해
            		- item은 유저 데이터의 item 값과 정확히 일치시켜
            		- 각 리스트 수는 입력과 동일하게 맞춰줘
            		- 다음은 피드백을 생성할 때 반드시 참고해야 하는 가이드라인이야:
            		""" + guidePrompt
        );

        Map<String, String> user = Map.of(
            "role", "user",
            "content", inputString
        );

        return List.of(system, user);
    }
	
	public List<Map<String, String>> getEditPrompt(RequestResponseDto requestResponseDto, String guidePrompt) {
		
		String inputString = getInputStringFromRequestResponseDto(requestResponseDto);
		
        Map<String, String> system = Map.of(
            "role", "system",
            "content", """ 
            		다음과 같은 JSON 형태로 출력해줘:
            		{
            		"첨삭": [
            			{
            				"item": "성장과정",
            				"comment": "내용이 추상적입니다. 구체적인 사례를 추가하면 좋겠습니다."
            				"documentType" : "경력기술서"
            			},
            			{
            				"item": "입사 후 포부",
            				"comment": "회사에 기여하고 싶은 방향이 드러나지 않습니다."
            				"documentType" : "경력기술서"
            			}
            			]
            		}
            		
            		규칙:
            		- documentType은 각 블록 제목인 [경력기술서], [자기소개서]에 따라 명시해
            		- item은 유저 데이터의 item 값과 정확히 일치시켜
            		- 각 리스트 수는 입력과 동일하게 맞춰줘
            		- 다음은 첨삭을 생성할 때 반드시 참고해야 하는 가이드라인이야:
            		""" + guidePrompt
        );

        Map<String, String> user = Map.of(
            "role", "user",
            "content", inputString
        );

        return List.of(system, user);
    }
	
	
	
	
	

	private String getInputStringFromRequestResponseDto(RequestResponseDto requestResponseDto) {

		Map<String, List<?>> resumeMap = jsonUtil.parseResumeMapFromResumeJson(requestResponseDto.getResumeJson());

		List<EducationResponseDto> educationResponseDtos = (List<EducationResponseDto>) resumeMap.get("educationResponseDtos");
		List<CareerResponseDto> careerResponseDtos = (List<CareerResponseDto>) resumeMap.get("careerResponseDtos");
		List<CertificationResponseDto> certificationResponseDtos = (List<CertificationResponseDto>) resumeMap.get("certificationResponseDtos");
		List<LanguageTestResponseDto> languageTestResponseDtos = (List<LanguageTestResponseDto>) resumeMap.get("languageTestResponseDtos");
		CareerDescriptionResponseDto careerDescriptionResponseDto = jsonUtil
				.parseCareerDescriptionJson(requestResponseDto.getCareerDescriptionJson());
		CoverLetterResponseDto coverLetterResponseDto = jsonUtil
				.parseCoverLetterJson(requestResponseDto.getCoverLetterJson());

		StringBuilder sb = new StringBuilder();

		sb.append("[지원자 기본 정보]\n");
		sb.append(String.format("- 지원 회사: %s\n", requestResponseDto.getTargetCompanyName()));
		sb.append(String.format("- 지원 직무: %s\n", requestResponseDto.getTargetJob()));
		sb.append(String.format("- 컨설팅 유형: %s\n", requestResponseDto.getConsultingType()));
		sb.append("\n");

		sb.append("[이력서 정보]\n");

		sb.append("▶ 학력\n");
		for (EducationResponseDto edu : educationResponseDtos) {
			sb.append(String.format("- %s %s / GPA: %.1f / 재학기간: %s ~ %s\n", edu.getSchoolName(), edu.getMajor(),
					edu.getGpa(), edu.getEnterDate(), edu.getGraduateDate()));
		}

		sb.append("\n▶ 경력\n");
		for (CareerResponseDto career : careerResponseDtos) {
			sb.append(String.format("- %s / %s / %s / %s / %s ~ %s\n", career.getCompanyName(), career.getJob(),
					career.getDepartment(), career.getPosition(), career.getStartDate(), career.getEndDate()));
		}

		sb.append("\n▶ 자격증\n");
		for (CertificationResponseDto cert : certificationResponseDtos) {
			sb.append(String.format("- %s (%s) / 취득일: %s\n", cert.getCertificationName(), cert.getIssuingOrg(),
					cert.getAcquisitionDate()));
		}

		sb.append("\n▶ 어학시험\n");
		for (LanguageTestResponseDto lang : languageTestResponseDtos) {
			sb.append(String.format("- %s / 점수: %s / 시험일: %s\n", lang.getTestName(), lang.getScore(),
					lang.getAcquisitionDate()));
		}

		sb.append("\n[경력기술서]\n");
		for (CareerDescriptionContentResponseDto cdc : careerDescriptionResponseDto.getContents()) {
			sb.append(String.format("- 항목: %s, 내용: %s\n", cdc.getItem(), cdc.getContent()));
		}

		sb.append("\n[자기소개서]\n");
		for (CoverLetterContentResponseDto cl : coverLetterResponseDto.getContents()) {
			sb.append(String.format("- 항목: %s, 내용: %s\n", cl.getItem(), cl.getContent()));
		}

		return sb.toString();
	}

}
