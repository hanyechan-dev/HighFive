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
@Table(name = "certification")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Certification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CERTIFICATION_ID")
    private Long certificationId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RESUME_ID")
	private Resume resume;
	
	@Column(name="CERTIFICATION_NAME", nullable = false)
	private String certificationName;
	
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
