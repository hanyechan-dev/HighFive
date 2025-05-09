package com.jobPrize.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "advertisement")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Advertisement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "advertisement_id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private Company company;

	@Column(name = "image_url", nullable = false)
	private String image_url;

	@Column(name = "start_date")
	private LocalDate startDate;

	@Column(name = "end_date")
	private LocalDate endDate;
}