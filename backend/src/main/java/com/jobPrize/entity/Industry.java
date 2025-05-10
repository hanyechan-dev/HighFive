package com.jobPrize.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "industry")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Industry {

	@Id
	@Column(name = "industry_id")
	private String id;

	@Column(name = "industry_name", nullable = false)
	private String industryName;

	@Column(name = "description")
	private String description;

	@OneToMany(mappedBy = "industry")
	private List<Company> companies;
}