package com.jobPrize.entity.company;



import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import com.jobPrize.customException.CustomDateFormatException;
import com.jobPrize.customException.CustomEnumMismatchException;
import com.jobPrize.customException.CustomNumberFormatException;
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
	
	@JoinColumn(name = "industry", nullable = false) //DB와 반대구조 업종클래스에서도 관계설정
	private String industry;

	@Column(name = "company_name", nullable = false)
	private String companyName;

	@Column(name = "representative_name", nullable = false)
	private String representativeName;
	
	@Column(name = "business_number", nullable = false)
	private String businessNumber;

	@Column(name = "company_address", nullable = false)
	private String companyAddress;

	@Column(name = "company_phone", nullable = false)
	private String companyPhone;
	
	@Column(name = "introduction")
	private String introduction;

	@Enumerated(EnumType.STRING)
	@Column(name = "type", nullable = false)
	private CompanyType companyType;

	@Column(name = "employee_count")
	private int employeeCount;

	@Column(name = "established_date", nullable = false)
	private LocalDate establishedDate;
	
	@Column(name = "logo_image_name")
	private String logoImageName;

	@OneToMany(mappedBy = "company", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Schedule> schedules = new ArrayList<>();
	
	@OneToMany(mappedBy = "company")
	private List<JobPosting> jobPostings = new ArrayList<>();

	@OneToMany(mappedBy = "company")
	private List<Proposal> proposals = new ArrayList<>();

	
	public void updateCompanyInfo(CompanyUpdateDto companyUpdateDto, String uuidName) {
		
	    LocalDate establishedDate;
	    try {
	        establishedDate = LocalDate.parse(companyUpdateDto.getEstablishedDate());
	    } catch (DateTimeParseException e) {
	        throw new CustomDateFormatException("설립일");
	    }

	    int employeeCount;
	    try {
	        employeeCount = Integer.parseInt(companyUpdateDto.getEmployeeCount());
	    } catch (NumberFormatException e) {
	        throw new CustomNumberFormatException("직원 수");
	    }

	    CompanyType type;
	    try {
	        type = CompanyType.valueOf(companyUpdateDto.getCompanyType());
	    } catch (IllegalArgumentException e) {
	        throw new CustomEnumMismatchException("CompanyType");
	    }
		
		this.companyName = companyUpdateDto.getCompanyName();
		this.representativeName = companyUpdateDto.getRepresentativeName();
		this.establishedDate = establishedDate;
		this.businessNumber = companyUpdateDto.getBusinessNumber();
		this.companyAddress = companyUpdateDto.getCompanyAddress();
		this.companyType = type;
		this.industry = companyUpdateDto.getIndustry();
		this.companyPhone = companyUpdateDto.getCompanyPhone();
		this.introduction = companyUpdateDto.getIntroduction();
		this.employeeCount = employeeCount;
		this.logoImageName = uuidName;
	}
	
	public static Company of(User user, CompanyCreateDto dto, String uuidName) {
	    LocalDate establishedDate;
	    try {
	        establishedDate = LocalDate.parse(dto.getEstablishedDate());
	    } catch (DateTimeParseException e) {
	        throw new CustomDateFormatException("설립일");
	    }

	    int employeeCount;
	    try {
	        employeeCount = Integer.parseInt(dto.getEmployeeCount());
	    } catch (NumberFormatException e) {
	        throw new CustomNumberFormatException("직원 수");
	    }

	    CompanyType type;
	    try {
	        type = CompanyType.valueOf(dto.getCompanyType());
	    } catch (IllegalArgumentException e) {
	        throw new CustomEnumMismatchException("CompanyType");
	    }

	    return Company.builder()
	        .user(user)
	        .companyName(dto.getCompanyName())
	        .representativeName(dto.getRepresentativeName())
	        .establishedDate(establishedDate)
	        .businessNumber(dto.getBusinessNumber())
	        .companyAddress(dto.getCompanyAddress())
	        .companyType(type)
	        .industry(dto.getIndustry())
	        .companyPhone(dto.getCompanyPhone())
	        .introduction(dto.getIntroduction())
	        .employeeCount(employeeCount)
	        .logoImageName(uuidName)
	        .build();
	}

	
}
