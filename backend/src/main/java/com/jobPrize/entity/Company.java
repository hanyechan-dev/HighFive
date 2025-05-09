package com.jobPrize.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "company") // 테이블명: company

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {

	@Id
	@Column(name = "USER_ID")
	private Long userId;

	@Enumerated(EnumType.STRING) // Enum 값을 String 형태로 저장
	@Column(name = "company_type", length = 20)
	private CompanyType companyType;

	@Column(name = "company_name", nullable = false, length = 100) // 컬럼명: company_name
	private String companyName;

	@Column(name = "registration_number", nullable = false, unique = true, length = 15) // 컬럼명: registration_number
	private String registrationNumber;

	@Column(name = "ceo_name", nullable = false, length = 50) // 컬럼명: ceo_name
	private String ceoName;

	@Column(name = "company_address", length = 200) // 컬럼명: company_address
	private String companyAddress;

	@Column(name = "company_phone_number", length = 20) // 컬럼명: company_phone_number
	private String companyPhoneNumber;

	@ManyToOne
	@JoinColumn(name = "industry_code") // 컬럼명: industry_code (FK)
	private Industry industry;

	@Column(name = "employee_count") // 컬럼명: employee_count
	private Integer employeeCount;

	@Column(name = "founding_date") // 컬럼명: founding_date
	private LocalDate foundingDate;

	@Column(name = "company_introduction", length = 1000) // 컬럼명: company_introduction
	private String companyIntroduction;

	@Column(name = "ceo_email", length = 100) // 컬럼명: ceo_email
	private String ceoEmail;

	@Column(name = "company_tel_number", length = 20) // 컬럼명: company_tel_number
	private String companyTelNumber;

	@OneToMany(mappedBy = "company")
	private List<JobPosting> recruitmentPosts;
}