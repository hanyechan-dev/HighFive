package com.jobPrize.entity.member;

import java.time.LocalDate;

import com.jobPrize.memberService.dto.languageTest.LanguageTestCreateDto;
import com.jobPrize.memberService.dto.languageTest.LanguageTestUpdateDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "language_test")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LanguageTest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="LANGUAGE_TEST_ID", nullable = false)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", nullable = false)
	private Member member;
	
	@Column(name="LANGUAGE_TYPE", nullable = false)
	private String languageType;
	
	@Column(name="TEST_NAME", nullable = false)
	private String testName;
	
	@Column(name="ISSUING_ORG", nullable = false)
	private String issuingOrg;
	
	@Column(name="GRADE")
	private String grade;
	
	@Column(name="SCORE")
	private String score;
	
	@Column(name="CERTIFICATION_NO", nullable = false)
	private String certificationNo;
	
	@Column(name="ACQUISITION_DATE", nullable = false)
	private LocalDate acquisitionDate;

	public void updateLanguageTest(LanguageTestUpdateDto languageTestUpdateDto) {
		this.languageType = languageTestUpdateDto.getLanguageType();
		this.testName = languageTestUpdateDto.getTestName();
		this.issuingOrg = languageTestUpdateDto.getIssuingOrg();
		this.grade = languageTestUpdateDto.getGrade();
		this.score = languageTestUpdateDto.getScore();
		this.certificationNo = languageTestUpdateDto.getCertificationNo();
		this.acquisitionDate = languageTestUpdateDto.getAcquisitionDate();
	}

	public static LanguageTest of(Member member, LanguageTestCreateDto languageTestCreateDto) {
		return LanguageTest.builder()
			.member(member)
			.languageType(languageTestCreateDto.getLanguageType())
			.testName(languageTestCreateDto.getTestName())
			.issuingOrg(languageTestCreateDto.getIssuingOrg())
			.grade(languageTestCreateDto.getGrade())
			.score(languageTestCreateDto.getScore())
			.certificationNo(languageTestCreateDto.getCertificationNo())
			.acquisitionDate(languageTestCreateDto.getAcquisitionDate())
			.build();
	}
	
	

}
