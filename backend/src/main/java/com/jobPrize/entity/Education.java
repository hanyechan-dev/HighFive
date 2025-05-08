package com.jobPrize.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "education")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Education {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="EDUCATION_ID")
    private Long educationId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RESUME_ID")
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

}
