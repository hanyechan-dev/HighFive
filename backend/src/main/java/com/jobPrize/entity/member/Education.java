package com.jobPrize.entity.member;

import java.time.LocalDate;

import com.jobPrize.dto.member.education.EducationCreateDto;
import com.jobPrize.dto.member.education.EducationUpdateDto;
import com.jobPrize.enumerate.EducationLevel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "education")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Education {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="EDUCATION_ID", nullable = false)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", nullable = false)
	private Member member;
	
	@Column(name="SCHOOL_NAME", nullable = false)
	private String schoolName;
	
	@Enumerated(EnumType.STRING)
	@Column(name="EDUCATION_LEVEL", nullable = false)
	private EducationLevel educationLevel;
	
	@Column(name="MAJOR")
	private String major;
	
	@Column(name="GPA")
	private String gpa;
	
	@Column(name="LOCATION", nullable = false)
	private String location;
	
	@Column(name="ENTER_DATE", nullable = false)
	private LocalDate enterDate;
	
	@Column(name="GRADUATE_DATE")
	private LocalDate graduateDate;

	public void updateEducation(EducationUpdateDto educationUpdateDto) {
		this.schoolName = educationUpdateDto.getSchoolName();
		this.educationLevel = EducationLevel.valueOf(educationUpdateDto.getEducationLevel());
		this.major = educationUpdateDto.getMajor();
		this.gpa = educationUpdateDto.getGpa();
		this.location = educationUpdateDto.getLocation();
		this.enterDate = educationUpdateDto.getEnterDate();
		this.graduateDate = educationUpdateDto.getGraduateDate();
	}

	public static Education of(Member member, EducationCreateDto educationCreateDto) {
		return Education.builder()
			.member(member)
			.schoolName(educationCreateDto.getSchoolName())
			.educationLevel(EducationLevel.valueOf(educationCreateDto.getEducationLevel()))
			.major(educationCreateDto.getMajor())
			.gpa(educationCreateDto.getGpa())
			.location(educationCreateDto.getLocation())
			.enterDate(educationCreateDto.getEnterDate())
			.graduateDate(educationCreateDto.getGraduateDate())
			.build();
	}
	
	

}
