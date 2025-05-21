package com.jobPrize.dto.member.coverLetter;

import java.util.List;

import com.jobPrize.entity.member.CoverLetter;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CoverLetterResponseDto {
	
	private Long id;
	
	private String title;
	
	List<CoverLetterContentResponseDto> contents;
	
	public static CoverLetterResponseDto of(CoverLetter coverLetter, List<CoverLetterContentResponseDto> coverLetterContentResponseDtos ) {
		return CoverLetterResponseDto.builder()
				.id(coverLetter.getId())
				.title(coverLetter.getTitle())
				.contents(coverLetterContentResponseDtos)
				.build();
	}
}
