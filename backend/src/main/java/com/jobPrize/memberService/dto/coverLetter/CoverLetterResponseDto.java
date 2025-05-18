package com.jobPrize.memberService.dto.coverLetter;

import java.util.List;
import java.util.stream.Collectors;

import com.jobPrize.entity.member.CoverLetter;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CoverLetterResponseDto {
	
	private Long id;
	
	private String title;
	
	List<CoverLetterContentResponseDto> contents;
	
	public static CoverLetterResponseDto from(CoverLetter coverLetter) {
		return CoverLetterResponseDto.builder()
				.id(coverLetter.getId())
				.title(coverLetter.getTitle())
				.contents(
						coverLetter.getCoverLetterContents()
						.stream()
						.map(CoverLetterContentResponseDto::from)
						.collect(Collectors.toList())
				)
				.build();
	}
}
