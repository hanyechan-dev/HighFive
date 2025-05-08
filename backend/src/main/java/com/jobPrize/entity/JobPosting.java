package com.jobPrize.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "job_posting")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobPosting {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_posting_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(name = "title", nullable = false, length = 50)
    private String title;

    @Column(name = "content", length = 500)
    private String content;

    @Column(name = "job", nullable = false, length = 20)
    private String job;

    @Column(name = "working_hours", nullable = false, length = 20)
    private String workingHours;

    @Column(name = "work_location", nullable = false, length = 10)
    private String workLocation;

    @Column(name = "career_type", nullable = false, length = 10)
    private String careerType;

    @Enumerated(EnumType.STRING)
    @Column(name = "education_level", nullable = false)
    private EducationLevel educationLevel;

    @Column(name = "salary")
    private Integer salary;

    @Column(name = "qualifications", length = 50)
    private String qualifications;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "expired_date", nullable = false)
    private LocalDate expiredDate;
}