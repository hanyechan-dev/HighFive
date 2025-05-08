package com.jobPrize.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "resume")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Resume {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="RESUME_ID")
    private Long resumeId;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	private Member member;
	
	@OneToMany(mappedBy = "resume")
	private List<Education> educations;
	
	@OneToMany(mappedBy = "resume")
	private List<Career> careers;
	
	@OneToMany(mappedBy = "resume")
	private List<Certification> certifications;
	
	@OneToMany(mappedBy = "resume")
	private List<LanguageTest> languageTests;

}
