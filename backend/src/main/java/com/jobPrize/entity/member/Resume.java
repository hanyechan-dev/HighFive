package com.jobPrize.entity.member;

import java.util.ArrayList;
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
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "resume")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Resume {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="RESUME_ID", nullable = false)
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", nullable = false)
	private Member member;
	
	@OneToMany(mappedBy = "resume")
	private List<Education> educations = new ArrayList<>();
	
	@OneToMany(mappedBy = "resume")
	private List<Career> careers = new ArrayList<>();
	
	@OneToMany(mappedBy = "resume")
	private List<Certification> certifications = new ArrayList<>();
	
	@OneToMany(mappedBy = "resume")
	private List<LanguageTest> languageTests = new ArrayList<>();

}
