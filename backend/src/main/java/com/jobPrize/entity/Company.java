package com.jobPrize.entity;


import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "company")
@NoArgsConstructor
@AllArgsConstructor
public class Company {

	@Id
	@Column(name = "user_id") 
	private Long Id;

	@OneToOne(fetch = FetchType.LAZY)
	@MapsId 
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "company_name")
	private String conpanyName;

	@Column(name = "representative_name")
	private String representativeName;

	@Column(name = "established_date")
	private LocalDate establishedDate;

	@Column(name = "business_number")
	private String businessNumber;

	@Column(name = "company_adress")
	private String companyAddress;

	@Enumerated(EnumType.STRING)
	@Column(name = "type")
	private CompanyType type;;

	@ManyToOne
	@JoinColumn(name = "industry") //DB와 반대구조 업종클래스에서도 관계설정
	private Industry industry;

	@Column(name = "company_phone")
	private String companyPhone;
	
	@Column(name = "introduction")
	private String introduction;
	
	@Column(name = "employee_count")
	private String employeeCount;
}
