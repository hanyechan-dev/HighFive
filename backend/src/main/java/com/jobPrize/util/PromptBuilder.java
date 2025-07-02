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
				다음과 같은 JSON 형식으로 출력하십시오.(아래 JSON 예시는 오직 형식을 보여주기 위한 것이며, content에 포함된 문장은 사용 금지입니다.):
				{
				    "피드백": [
				        {
				            "item": "작성 항목 예시",
				            "content": "작성 내용 예시",
				            "documentType": "경력기술서"
				        }
				    ]
				}

				※ 규칙
				- 위 형식은 출력의 예시일 뿐이며, 실제 출력 시 반드시 입력 데이터에 기반한 새로운 피드백을 작성하십시오. 예시 문장을 그대로 복사하지 마십시오.
				- 객체는 반드시 "피드백" key로 시작합니다.
				- "피드백" key의 밸류인 list 내부 객체는 "item", "content", "documentType" 의 key만을 가집니다.
				- "item" key의 value는 유저가 작성한 원본 데이터의 "item" key의 value와 완전히 일치해야 합니다.
				- 생성된 피드백 내용은 반드시 "content" key에 넣으십시오
				- `documentType`은 각 블록 제목인 [경력기술서], [자기소개서]에 따라 정확히 명시합니다.
				- 리스트의 길이(객체 수)는 입력과 반드시 일치해야 합니다.
				- 출력은 반드시 위 JSON 출력 예시와 key 이름, 구조, 대소문자까지 완전히 일치해야 하며, 절대로 "첨삭", "comment" 등의 다른 key를 사용하지 마십시오.
				- 절대로 예시 JSON에 포함된 "content"의 예시 문장을 그대로 사용하지 마십시오.
				- 모든 content 값은 입력 데이터에 기반해 새롭게 생성해야 합니다.
				- 만약 예시 문장을 그대로 출력하면 잘못된 결과로 간주됩니다.
				- 만약 유저의 입력이 무의미하거나 임의의 문자열(예: "asdasdasd", "ㅋㅋㅋㅋ", "1234" 등)로 구성되어 있어 정상적인 피드백을 작성할 수 없다고 판단되면, 해당 항목의 "content" 값에 다음과 같은 문구를 그대로 출력하십시오:
				"요청하신 문장은 피드백하기에 부적절합니다."
				
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
				다음과 같은 JSON 형식으로 출력하십시오.(아래 JSON 예시는 오직 형식을 보여주기 위한 것이며, content에 포함된 문장은 사용 금지입니다.):
				{
				    "첨삭": [
				        {
				            "item": "작성 항목 예시",
				            "content": "작성 내용 예시",
				            "documentType": "경력기술서"
				        }
				    ]
				}

				※ 규칙
				- 위 형식은 출력의 예시일 뿐이며, 실제 출력 시 반드시 입력 데이터에 기반하여 더 나은 예문을 작성하십시오. 예시 문장을 그대로 복사하지 마십시오.
				- 첨삭이라 함은 기존의 문장 중 일부를 개선된 문장으로 바꾸는 것을 의미합니다.
				- content는 절대로 코멘트 형식이 아니라, 원문을 개선한 하나의 완전한 문장 또는 단락으로 작성하십시오.
				- 하나의 의미를 가지는 문장의 집합을 '문단'이라 정의합니다.
				- content를 작성할 때 문단과 문단 사이에 반드시 두 개의 연속 개행문자(\n\n)를 삽입하십시오.
				- 문단은 주제가 바뀌거나 설명이 마무리될 때 구분합니다.
				- 문단 내에서는 개행하지 않으며, 문단 구분 시에만 \n\n을 사용합니다.
				- "학교 도서관 좌석 시스템의 문제점을 해결하기 위해 타 학교의 운영 사례를 조사한 결과, 우리 학교의 시스템에는 좌석 선택 및 시간 연장 기능이 부족하다는 점을 발견했습니다.\n\n따라서 앱을 통해 미리 좌석을 선택하고 QR코드를 활용하여 시간 연장 기능을 추가하는 해결책을 제안했습니다.\n\n기획안을 발표한 후, 교수님의 도움으로 개선된 시스템이 도입되었으며, 좌석 선택 시간이 평균 10분에서 1분으로 줄었습니다.\n이 경험을 통해 다양한 관점에서 문제를 접근하여 해결책을 모 색하는 역량을 키웠습니다." 와 같이 개행문자 삽입 규칙을 지키십시오. 
				- 객체는 반드시 "첨삭" key로 시작합니다.
				- "첨삭" key의 밸류인 list 내부 객체는 "item", "content", "documentType" 의 key만을 가집니다.
				- "item" key의 value는 유저가 작성한 원본 데이터의 "item" key의 value와 완전히 일치해야 합니다.
				- 생성된 첨삭 내용은 반드시 "content" key에 넣으십시오
				- `documentType`은 각 블록 제목인 [경력기술서], [자기소개서]에 따라 정확히 명시합니다.
				- 리스트의 길이(객체 수)는 입력과 반드시 일치해야 합니다.
				- 출력은 반드시 위 JSON 출력 예시와 key 이름, 구조, 대소문자까지 완전히 일치해야 하며, 절대로 "피드백", "comment" 등의 다른 key를 사용하지 마십시오.
				- 절대로 예시 JSON에 포함된 "content"의 예시 문장을 그대로 사용하지 마십시오.
				- 모든 content 값은 입력 데이터에 기반해 새롭게 생성해야 합니다.
				- 만약 예시 문장을 그대로 출력하면 잘못된 결과로 간주됩니다.
				- 만약 유저의 입력이 무의미하거나 임의의 문자열(예: "asdasdasd", "ㅋㅋㅋㅋ", "1234" 등)로 구성되어 있어 정상적인 피드백을 작성할 수 없다고 판단되면, 해당 항목의 "content" 값에 다음과 같은 문구를 그대로 출력하십시오:
				"요청하신 문장은 첨삭하기에 부적절합니다."
				
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
			sb.append(String.format("- %s %s / GPA: %s / 재학기간: %s ~ %s\n", edu.getSchoolName(), edu.getMajor(),
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
		
		if (careerDescriptionResponseDto == null || careerDescriptionResponseDto.getContents().isEmpty()) {
		
		} else {
			sb.append("\n[경력기술서]\n");
			for (CareerDescriptionContentResponseDto cdc : careerDescriptionResponseDto.getContents()) {
				sb.append(String.format("- 항목: %s, 내용: %s\n", cdc.getItem(), cdc.getContent()));
			}
		}

		
		if (coverLetterResponseDto == null || coverLetterResponseDto.getContents().isEmpty()) {

		} else {
			sb.append("\n[자기소개서]\n");
			for (CoverLetterContentResponseDto cl : coverLetterResponseDto.getContents()) {
				sb.append(String.format("- 항목: %s, 내용: %s\n", cl.getItem(), cl.getContent()));
			}
		}

		return sb.toString();
	}

}
