package com.jobPrize.dto.member.coverLetter;

import java.time.LocalDate;
import java.util.List;

import com.jobPrize.entity.member.CoverLetter;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CoverLetterResponseDto {
	
	private Long id;
	
	private String title;
	
	private LocalDate createdDate;
	
	List<CoverLetterContentResponseDto> contents;
	
	public static CoverLetterResponseDto of(CoverLetter coverLetter, List<CoverLetterContentResponseDto> coverLetterContentResponseDtos ) {
		return CoverLetterResponseDto.builder()
				.id(coverLetter.getId())
				.createdDate(coverLetter.getCreatedDate())
				.title(coverLetter.getTitle())
				.contents(coverLetterContentResponseDtos)
				.build();
	}
}
