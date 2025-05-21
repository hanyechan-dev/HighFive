package com.jobPrize.dto.member.coverLetter;

import java.time.LocalDate;

import com.jobPrize.entity.member.CoverLetter;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CoverLetterSummaryDto {
	
	private Long id;
	
	private String title;
	
	private LocalDate createdDate;
	
	public static CoverLetterSummaryDto from(CoverLetter coverLetter) {
		return CoverLetterSummaryDto.builder()
				.id(coverLetter.getId())
				.title(coverLetter.getTitle())
				.createdDate(coverLetter.getCreatedDate())
				.build();
	}
}
