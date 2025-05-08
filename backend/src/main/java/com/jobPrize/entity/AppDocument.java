package com.jobPrize.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "app_document")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AppDocument {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="APP_DOCUMENT_ID")
    private Long appDocumentId;
	
	@Column(name = "resume_json", columnDefinition = "TEXT")
	private String resumeJson;

	@Column(name = "cover_letter_json", columnDefinition = "TEXT", nullable = false)
	private String coverLetterJson;

	@Column(name = "career_description_json", columnDefinition = "TEXT")
	private String careerDescriptionJson;

	
}
