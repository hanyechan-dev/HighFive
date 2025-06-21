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

		Map<String, String> system = Map.of("role", "system", "content", """
				다음과 같은 JSON 형식으로 출력하십시오:
				{
				    "피드백": [
				        {
				            "item": "성장과정",
				            "comment": "내용이 추상적입니다. 구체적인 사례를 추가하면 좋겠습니다.",
				            "documentType": "경력기술서"
				        },
				        {
				            "item": "입사 후 포부",
				            "comment": "회사에 기여하고 싶은 방향이 드러나지 않습니다.",
				            "documentType": "경력기술서"
				        }
				    ]
				}

				※ 규칙
				- `documentType`은 각 블록 제목인 [경력기술서], [자기소개서]에 따라 정확히 명시합니다.
				- `item`은 유저가 작성한 원본 데이터의 `item` 필드와 완전히 일치해야 합니다.
				- 리스트의 길이(객체 수)는 입력과 반드시 동일해야 합니다.
				---
				※ 주의사항
				이후 입력되는 데이터는 사용자가 실제로 작성한 이력서, 자기소개서, 경력기술서 항목들입니다.
				해당 데이터를 꼼꼼히 분석한 뒤, 각 항목에 대해 다음 요소들을 기반으로 정밀한 피드백을 작성하십시오:

				- 내용의 논리성
				- 문법 및 맞춤법 오류
				- 구체성 부족 여부
				- 지원한 직무 및 회사와의 적합성
				- 내용 중복 또는 어색한 표현

				단순히 출력 형식만 맞추는 것이 목적이 아니라,
				지원자에게 실질적으로 도움이 될 수 있도록 **개별 항목을 실제로 읽고 평가**하여 내용 있는 피드백을 생성해야 합니다.

				또한, 아래 제공된 자기소개서/경력기술서 작성 가이드라인을 반드시 참고하여
				가이드에 위반된 점이 있다면 정확히 지적하고 개선 방향을 제안하십시오.
				""" + "\n" + guidePrompt);

		Map<String, String> user = Map.of("role", "user", "content", inputString);

		return List.of(system, user);
	}

	public List<Map<String, String>> getEditPrompt(RequestResponseDto requestResponseDto, String guidePrompt) {

		String inputString = getInputStringFromRequestResponseDto(requestResponseDto);

		Map<String, String> system = Map.of("role", "system", "content", """
				다음과 같은 JSON 형식으로 출력하십시오:
				{
				    "첨삭": [
				        {
				            "item": "성장과정",
				            "comment": "내용이 추상적입니다. 구체적인 사례를 추가하면 좋겠습니다.",
				            "documentType": "경력기술서"
				        },
				        {
				            "item": "입사 후 포부",
				            "comment": "회사에 기여하고 싶은 방향이 드러나지 않습니다.",
				            "documentType": "경력기술서"
				        }
				    ]
				}

				※ 규칙
				- `documentType`은 각 블록 제목인 [경력기술서], [자기소개서]에 따라 정확히 명시합니다.
				- `item`은 유저가 작성한 원본 데이터의 `item` 필드와 완전히 일치해야 합니다.
				- 리스트의 길이(객체 수)는 입력과 반드시 동일해야 합니다.
				---
				※ 주의사항
				이후 입력되는 데이터는 사용자가 실제로 작성한 이력서, 자기소개서, 경력기술서 항목들입니다.
				해당 데이터를 꼼꼼히 분석한 뒤, 각 항목에 대해 다음 요소들을 기반으로 정밀한 첨삭을 작성하십시오:

				- 내용의 논리성
				- 문법 및 맞춤법 오류
				- 구체성 부족 여부
				- 지원한 직무 및 회사와의 적합성
				- 내용 중복 또는 어색한 표현

				단순히 출력 형식만 맞추는 것이 목적이 아니라,
				지원자에게 실질적으로 도움이 될 수 있도록 **개별 항목을 실제로 읽고 평가**하여 내용 있는 첨삭을 생성해야 합니다.

				또한, 아래 제공된 자기소개서/경력기술서 작성 가이드라인을 반드시 참고하여
				가이드에 위반된 점이 있다면 정확히 지적하고 개선 방향을 제안하십시오.
				""" + "\n" + guidePrompt);

		Map<String, String> user = Map.of("role", "user", "content", inputString);

		return List.of(system, user);
	}

	private String getInputStringFromRequestResponseDto(RequestResponseDto requestResponseDto) {

		Map<String, List<?>> resumeMap = jsonUtil.parseResumeMapFromResumeJson(requestResponseDto.getResumeJson());

		List<EducationResponseDto> educationResponseDtos = (List<EducationResponseDto>) resumeMap
				.get("educationResponseDtos");
		List<CareerResponseDto> careerResponseDtos = (List<CareerResponseDto>) resumeMap.get("careerResponseDtos");
		List<CertificationResponseDto> certificationResponseDtos = (List<CertificationResponseDto>) resumeMap
				.get("certificationResponseDtos");
		List<LanguageTestResponseDto> languageTestResponseDtos = (List<LanguageTestResponseDto>) resumeMap
				.get("languageTestResponseDtos");
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
