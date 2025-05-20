package com.jobPrize.entity.memToCom;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.jobPrize.entity.company.JobPosting;
import com.jobPrize.entity.member.Member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "application")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Application {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="APPLICATION_ID")
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private Member member;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "JOB_POSTING_ID", nullable = false)
    private JobPosting jobPosting;
    
	@Column(name = "resume_json", columnDefinition = "TEXT", nullable = false)
	private String resumeJson;

	@Column(name = "career_description_json", columnDefinition = "TEXT")
	private String careerDescriptionJson;
	
	@Column(name = "cover_letter_json", columnDefinition = "TEXT", nullable = false)
	private String coverLetterJson;
    
	@CreatedDate
	@Column(nullable = false, name="CREATED_DATE")
	private LocalDate createdDate;

	
	@OneToOne(mappedBy = "application", fetch = FetchType.LAZY)
	private Pass pass;

}
