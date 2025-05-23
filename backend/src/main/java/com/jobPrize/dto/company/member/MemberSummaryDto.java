package com.jobPrize.dto.company.member;

import com.jobPrize.entity.common.User;
import com.jobPrize.entity.memToCom.EducationLevel;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberSummaryDto {
	
	private Long id;
	
	private String name;
	
	private String job;
	
	private boolean hasCareer;
	
	private int similarityScore;
	
	private EducationLevel educationLevel;
	
	private boolean isInterested;

	public static MemberSummaryDto of(User user, boolean hasCareer, String job, int similarityScore, EducationLevel educationLevel, boolean isInterested) {
		
		return MemberSummaryDto.builder()
			.id(user.getId())
			.name(user.getName())
			.job(job)
			.hasCareer(hasCareer)
			.similarityScore(similarityScore)
			.educationLevel(educationLevel)
			.isInterested(isInterested)
			.build();
	}
}