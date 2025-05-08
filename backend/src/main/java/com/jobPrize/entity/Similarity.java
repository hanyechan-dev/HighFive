package com.jobPrize.entity;

import java.time.LocalDate;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "similarity")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Similarity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="SIMILARITY_ID")
    private Long similarityId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	private Member member;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "JOB_POSTING_ID")
	private JobPosting jobPosting;
	
	@Column(name = "SCORE", nullable = false)
	private int score;
	
    @Column(nullable = false,name="CREATED_DATE")
    private LocalDate createdDate;
	

}
