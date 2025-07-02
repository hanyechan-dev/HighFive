package com.jobPrize.dto.member.coverLetter;

import java.time.LocalDate;
import java.util.List;

import com.jobPrize.entity.member.CoverLetter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
