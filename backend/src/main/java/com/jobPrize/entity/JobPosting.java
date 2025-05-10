package com.jobPrize.entity;

<<<<<<< HEAD
public class JobPosting {

}
=======
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

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
	private Integer salary;

	@Column(name = "requirement")
	private String requirement;
	
	@Column(name = "created_date", nullable = false)
	private LocalDate createdDate;

	@Column(name = "expired_date", nullable = false)
	private LocalDate expiredDate;
}
>>>>>>> origin/COMPANY
