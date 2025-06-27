package com.jobPrize.entity.company;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.jobPrize.dto.company.jobPosting.JobPostingCreateDto;
import com.jobPrize.dto.company.jobPosting.JobPostingUpdateDto;
import com.jobPrize.entity.memToCom.Application;
import com.jobPrize.entity.memToCom.Similarity;
import com.jobPrize.enumerate.EducationLevel;

import jakarta.persistence.CascadeType;
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
@Getter
@Entity
@Table(name = "job_posting")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobPosting {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "job_posting_id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private Company company;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "content")
	private String content;

	@Column(name = "job", nullable = false)
	private String job;

	@Column(name = "requirement")
	private String requirement;

	@Column(name = "working_hours", nullable = false)
	private String workingHours;

	@Column(name = "work_location", nullable = false)
	private String workLocation;

	@Column(name = "career_type", nullable = false)
	private String careerType;

	@Enumerated(EnumType.STRING)
	@Column(name = "education_level", nullable = false)
	private EducationLevel educationLevel;

	@Column(name = "salary")
	private int salary;
	
	@Column(name = "job-posting-vector")
	private String jobPostingVector;

	@Column(name = "created_date", nullable = false)
	private LocalDate createdDate;

	@Column(name = "expired_date", nullable = false)
	private LocalDate expiredDate;

	@OneToMany(mappedBy = "jobPosting" , cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<JobPostingImage> jobPostingImages = new ArrayList<>();

	@OneToMany(mappedBy = "jobPosting")
	private List<Application> applications = new ArrayList<>();
	
	@OneToMany(mappedBy = "jobPosting" , cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Similarity> similarities = new ArrayList<>();


	public void updateJobPostingInfo(JobPostingUpdateDto jobPostingUpdateDto) {
		this.title = jobPostingUpdateDto.getTitle();
		this.job = jobPostingUpdateDto.getJob();
		this.workingHours = jobPostingUpdateDto.getWorkingHours();
		this.workLocation = jobPostingUpdateDto.getWorkLocation();
		this.careerType = jobPostingUpdateDto.getCareerType();
		this.educationLevel = EducationLevel.valueOf(jobPostingUpdateDto.getEducationLevel());
		this.salary = jobPostingUpdateDto.getSalary();
		this.requirement = jobPostingUpdateDto.getRequirement();
	}

	public static JobPosting of(Company company, JobPostingCreateDto jobPostingCreateDto, String jobPostingVector) {
		return JobPosting.builder()
			.company(company)
			.title(jobPostingCreateDto.getTitle())
			.content(jobPostingCreateDto.getContent())
			.job(jobPostingCreateDto.getJob())
			.workingHours(jobPostingCreateDto.getWorkingHours())
			.workLocation(jobPostingCreateDto.getWorkLocation())
			.careerType(jobPostingCreateDto.getCareerType())
			.educationLevel(EducationLevel.valueOf(jobPostingCreateDto.getEducationLevel()))
			.salary(jobPostingCreateDto.getSalary())
			.jobPostingVector(jobPostingVector)
			.createdDate(LocalDate.now())
			.expiredDate(LocalDate.now().plusMonths(1))
			.requirement(jobPostingCreateDto.getRequirement())
			.build();
	}
	
}
