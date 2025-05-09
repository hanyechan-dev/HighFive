package com.jobPrize.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "job_posting_image")
@NoArgsConstructor
@AllArgsConstructor

public class JobPostingImage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "job_posting_image_id")
	private Long jobPostingImageId;

	@ManyToOne
	@JoinColumn(name = "job_posting_id", nullable = false)
	private JobPosting jobPosting;

	@Column(name = "image_url", nullable = false)
	private String imageUrl;

}