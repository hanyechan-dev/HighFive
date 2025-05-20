package com.jobPrize.entity.member;

import java.time.LocalDate;

import com.jobPrize.memberService.dto.career.CareerCreateDto;
import com.jobPrize.memberService.dto.career.CareerUpdateDto;

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
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "career")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Career {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CAREER_ID")
    private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", nullable = false)
	private Member member;
	
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
	
	public void updateCareer(CareerUpdateDto careerUpdateDto) {
		this.companyName = careerUpdateDto.getCompanyName();
		this.job = careerUpdateDto.getJob();
		this.department = careerUpdateDto.getDepartment();
		this.position = careerUpdateDto.getPosition();
		this.startDate = careerUpdateDto.getStartDate();
		this.endDate = careerUpdateDto.getEndDate();
	}

	public static Career of(Member member, CareerCreateDto careerCreateDto) {
		return Career.builder()
			.member(member)
			.companyName(careerCreateDto.getCompanyName())
			.job(careerCreateDto.getJob())
			.department(careerCreateDto.getDepartment())
			.position(careerCreateDto.getPosition())
			.startDate(careerCreateDto.getStartDate())
			.endDate(careerCreateDto.getEndDate())
			.build();
	}
	
	
	

}
