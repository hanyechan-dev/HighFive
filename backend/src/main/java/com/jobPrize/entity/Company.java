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
@Table(name = "company") 

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {

	@Id
	@Column(name = "USER_ID")
	private Long userId;

	@Enumerated(EnumType.STRING) 
	@Column(name = "company_type")
	private CompanyType companyType;

	@Column(name = "company_name", nullable = false)
	private String companyName;

	@Column(name = "registration_number", nullable = false, unique = true)
	private String registrationNumber;

	@Column(name = "ceo_name", nullable = false)
	private String ceoName;

	@Column(name = "company_address")
	private String companyAddress;

	@Column(name = "company_phone_number")
	private String companyPhoneNumber;

	@ManyToOne
	@JoinColumn(name = "industry_code")

	@Column(name = "employee_count")
	private Integer employeeCount;

	@Column(name = "founding_date")
	private LocalDate foundingDate;

	@Column(name = "company_introduction")
	private String companyIntroduction;

	@Column(name = "ceo_email")
	private String ceoEmail;

	@Column(name = "company_tel_number")
	private String companyTelNumber;

	@OneToMany(mappedBy = "company")
	private List<JobPosting> recruitmentPosts;
}