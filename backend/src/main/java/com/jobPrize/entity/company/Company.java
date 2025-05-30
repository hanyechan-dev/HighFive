package com.jobPrize.entity.company;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.jobPrize.dto.company.company.CompanyCreateDto;
import com.jobPrize.dto.company.company.CompanyUpdateDto;
import com.jobPrize.entity.common.User;
import com.jobPrize.entity.memToCom.Proposal;
import com.jobPrize.enumerate.CompanyType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@Entity
@Table(name = "company")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {

	@Id
	@Column(name="USER_ID", nullable = false)
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	@JoinColumn(name="USER_ID", nullable = false)
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "industry", nullable = false) //DB와 반대구조 업종클래스에서도 관계설정
	private String industry;

	@Column(name = "company_name", nullable = false)
	private String companyName;

	@Column(name = "representative_name", nullable = false)
	private String representativeName;
	
	@Column(name = "business_number", nullable = false)
	private String businessNumber;

	@Column(name = "company_adress", nullable = false)
	private String companyAddress;

	@Column(name = "company_phone", nullable = false)
	private String companyPhone;
	
	@Column(name = "introduction")
	private String introduction;

	@Enumerated(EnumType.STRING)
	@Column(name = "type", nullable = false)
	private CompanyType type;

	@Column(name = "employee_count")
	private int employeeCount;

	@Column(name = "established_date", nullable = false)
	private LocalDate establishedDate;

	@OneToMany(mappedBy = "company", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Schedule> schedules = new ArrayList<>();
	
	@OneToMany(mappedBy = "company")
	private List<JobPosting> jobPostings = new ArrayList<>();

	@OneToMany(mappedBy = "company")
	private List<Proposal> proposals = new ArrayList<>();

	
	public void updateCompanyInfo(CompanyUpdateDto companyUpdateDto) {
		this.companyName = companyUpdateDto.getCompanyName();
		this.representativeName = companyUpdateDto.getRepresentativeName();
		this.establishedDate = companyUpdateDto.getEstablishedDate();
		this.businessNumber = companyUpdateDto.getBusinessNumber();
		this.companyAddress = companyUpdateDto.getCompanyAddress();
		this.type = companyUpdateDto.getType();
		this.industry = companyUpdateDto.getIndustry();
		this.companyPhone = companyUpdateDto.getCompanyPhone();
		this.introduction = companyUpdateDto.getIntroduction();
		this.employeeCount = companyUpdateDto.getEmployeeCount();

	}
	
	public static Company of(User user, CompanyCreateDto companyCreateDto) {
		return Company.builder()
			.id(user.getId())
			.user(user)
			.companyName(companyCreateDto.getCompanyName())
			.representativeName(companyCreateDto.getRepresentativeName())
			.establishedDate(companyCreateDto.getEstablishedDate())
			.businessNumber(companyCreateDto.getBusinessNumber())
			.companyAddress(companyCreateDto.getCompanyAddress())	
			.type(companyCreateDto.getType())
			.industry(companyCreateDto.getIndustry())
			.companyPhone(companyCreateDto.getCompanyPhone())
			.introduction(companyCreateDto.getIntroduction())
			.employeeCount(companyCreateDto.getEmployeeCount())
			.build();
	}

	
}
