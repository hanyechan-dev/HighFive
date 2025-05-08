package com.jobPrize.entity;

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
@Table(name = "career_description_content")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CareerDescriptionContent {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CAREER_DESCRIPTION_CONTENT_ID")
    private Long careerDescriptionContentId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CAREER_DESCRIPTION_ID")
	private CareerDescription careerDescription;
	
	@Column(name="ITEM", nullable = false)
	private String item;
	
	@Column(name="CONTENT", nullable = false)
	private String content;

}
