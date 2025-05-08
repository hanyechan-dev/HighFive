package com.jobPrize.entity;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "language_test")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LanguageTest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="LANGUAGE_TEST_ID")
    private Long languageTestId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RESUME_ID")
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

}
