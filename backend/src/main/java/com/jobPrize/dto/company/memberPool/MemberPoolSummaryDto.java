package com.jobPrize.dto.company.memberPool;

import com.jobPrize.entity.memToCom.Similarity;
import com.jobPrize.entity.member.Member;
import com.jobPrize.enumerate.EducationLevel;
import com.jobPrize.enumerate.GenderType;
import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberPoolSummaryDto {
	
	private Long id;
	
	private String name;

	private GenderType genderType;

	private LocalDate birthDate;
	
	private String job;
	
	private boolean hasCareer;
	
	private int similarityScore;
	
	private EducationLevel educationLevel;

	public static MemberPoolSummaryDto of(Similarity similarity, boolean hasCareer, String job, EducationLevel educationLevel) {
		Member member = similarity.getMember();
		return MemberPoolSummaryDto.builder()
			.id(member.getId())
			.name(member.getUser().getName())
			.genderType(member.getUser().getGenderType())
			.birthDate(member.getUser().getBirthDate())
			.job(job)
			.hasCareer(hasCareer)
			.similarityScore(similarity.getScore())
			.educationLevel(educationLevel)
			.build();
	}
}