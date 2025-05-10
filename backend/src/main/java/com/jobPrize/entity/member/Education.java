package com.jobPrize.entity.member;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.jobPrize.entity.memToCom.EducationLevel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "education")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Education {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="EDUCATION_ID", nullable = false)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RESUME_ID", nullable = false)
	private Resume resume;
	
	@Column(name="SCHOOL_NAME", nullable = false)
	private String schoolName;
	
	@Enumerated(EnumType.STRING)
	@Column(name="EDUCATION_LEVEL", nullable = false)
	private EducationLevel educationLevel;
	
	@Column(name="MAJOR")
	private String major;
	
	@Column(name="GPA")
	private BigDecimal gpa;
	
	@Column(name="LOCATION", nullable = false)
	private String location;
	
	@Column(name="ENTER_DATE", nullable = false)
	private LocalDate enterDate;
	
	@Column(name="GRADUATE_DATE")
	private LocalDate graduateDate;

	public void updateEducation(String schoolName, EducationLevel educationLevel, String major, BigDecimal gpa, String location,
			LocalDate enterDate, LocalDate graduateDate) {
		this.schoolName = schoolName;
		this.educationLevel = educationLevel;
		this.major = major;
		this.gpa = gpa;
		this.location = location;
		this.enterDate = enterDate;
		this.graduateDate = graduateDate;
	}
	
	

}
