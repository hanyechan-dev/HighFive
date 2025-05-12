package com.jobPrize.entity.company;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

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
	private List<Company> companies = new ArrayList<>();
}