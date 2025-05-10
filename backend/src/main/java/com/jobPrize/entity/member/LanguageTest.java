package com.jobPrize.entity.member;

import java.time.LocalDate;

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
	@JoinColumn(name = "RESUME_ID", nullable = false)
	private Resume resume;
	
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

	public void updateLanguageTest(String languageType, String testName, String issuingOrg, String grade, String score,
			String certificationNo, LocalDate acquisitionDate) {
		this.languageType = languageType;
		this.testName = testName;
		this.issuingOrg = issuingOrg;
		this.grade = grade;
		this.score = score;
		this.certificationNo = certificationNo;
		this.acquisitionDate = acquisitionDate;
	}
	
	

}
