package com.jobPrize.dto.member.languageTest;

import java.time.LocalDate;

import com.jobPrize.entity.member.LanguageTest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LanguageTestResponseDto {
	

	private Long id;
	

	private String languageType;


	private String testName;


	private String issuingOrg;


	private String grade;


	private String score;


	private String certificationNo;


	private LocalDate acquisitionDate;
	
	public static LanguageTestResponseDto from(LanguageTest languageTest) {
		return LanguageTestResponseDto.builder()
				.id(languageTest.getId())
				.languageType(languageTest.getLanguageType())
				.testName(languageTest.getTestName())
				.issuingOrg(languageTest.getIssuingOrg())
				.grade(languageTest.getGrade())
				.score(languageTest.getScore())
				.certificationNo(languageTest.getCertificationNo())
				.acquisitionDate(languageTest.getAcquisitionDate())
				.build();
	}
}
