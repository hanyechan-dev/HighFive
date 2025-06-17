package com.jobPrize.dto.company.memberPool;

import java.time.LocalDate;

import com.jobPrize.entity.memToCom.Similarity;
import com.jobPrize.entity.member.Member;
import com.jobPrize.enumerate.EducationLevel;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberPoolSummaryDto {
	
	private Long id;
	
	private String name;
	
	private String job;
	
	private String genderType;
	
	private LocalDate birthDate;
	
	private boolean hasCareer;
	
	private int similarityScore;
	
	private String educationLevel;

	public static MemberPoolSummaryDto of(Similarity similarity, boolean hasCareer, String job, EducationLevel educationLevel) {
		Member member = similarity.getMember();
		return MemberPoolSummaryDto.builder()
			.id(member.getId())
			.name(member.getUser().getName())
			.genderType(member.getUser().getGenderType().name())
			.birthDate(member.getUser().getBirthDate())
			.job(job)
			.hasCareer(hasCareer)
			.similarityScore(similarity.getScore())
			.educationLevel(educationLevel.name())
			.build();
	}
}