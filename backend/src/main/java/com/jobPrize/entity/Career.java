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
@Table(name = "career")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Career {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CAREER_ID")
    private Long careerId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RESUME_ID")
	private Resume resume;
	
	@Column(name="COMPANY_NAME", nullable = false)
	private String companyName;
	
	@Column(name="JOB", nullable = false)
	private String job;
	
	@Column(name="DEPARTMENT", nullable = false)
	private String department;
	
	@Column(name="POSITION", nullable = false)
	private String position;
	
	@Column(name="START_DATE", nullable = false)
	private LocalDate startDate;
	
	@Column(name="END_DATE")
	private LocalDate endDate;

}
