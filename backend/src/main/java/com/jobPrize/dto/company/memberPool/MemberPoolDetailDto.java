package com.jobPrize.dto.company.memberPool;

import java.time.LocalDate;

import com.jobPrize.dto.member.career.CareerResponseDto;
import com.jobPrize.dto.member.certification.CertificationResponseDto;
import com.jobPrize.dto.member.education.EducationResponseDto;
import com.jobPrize.dto.member.languageTest.LanguageTestResponseDto;
import com.jobPrize.entity.common.GenderType;
import com.jobPrize.entity.common.User;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberPoolDetailDto {
	
	private String name;
	
	private String email;
	
	private GenderType gender;
	
	private LocalDate birthDate;
	
	private boolean hasCareer;
	
	private String job;
	
	private String phone;

	private EducationResponseDto educationResponseDto;
	
	private CareerResponseDto  careerResponseDto;
	
	private CertificationResponseDto certificationResponseDto;
	
	private LanguageTestResponseDto languageTestResponseDto;
	
	private boolean isInterested;

	public static MemberPoolDetailDto of(User user, boolean hasCareer, String job, EducationResponseDto educationResponseDto,
				CareerResponseDto  careerResponseDto,CertificationResponseDto certificationResponseDto,
				LanguageTestResponseDto languageTestResponseDto, boolean isInterested) {
		
		return MemberPoolDetailDto.builder()
			.name(user.getName())
			.email(user.getEmail())
			.gender(user.getGenderType())
			.birthDate(user.getBirthDate())
			.hasCareer(hasCareer)
			.job(job)
			.phone(user.getPhone())
			.educationResponseDto(educationResponseDto)
			.careerResponseDto(careerResponseDto)
			.certificationResponseDto(certificationResponseDto)
			.languageTestResponseDto(languageTestResponseDto)
			.isInterested(isInterested)
			.build();
	}
}