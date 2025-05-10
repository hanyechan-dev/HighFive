package com.jobPrize.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pass")
@NoArgsConstructor
@AllArgsConstructor
public class Pass {

	@Id
	@Column(name = "pass_id")
	private String id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "application_id")
	private Application application;  // Application 클래스가 없어서 오류
	
	@Column(name = "created_date", nullable = false)
	private LocalDate createdDate;
}
