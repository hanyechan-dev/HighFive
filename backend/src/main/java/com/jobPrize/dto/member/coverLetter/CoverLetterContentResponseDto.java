package com.jobPrize.dto.member.coverLetter;

import com.jobPrize.entity.member.CoverLetterContent;

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
public class CoverLetterContentResponseDto {

	private Long id;
	
	private String item;

	private String content;
	
	public static CoverLetterContentResponseDto from(CoverLetterContent coverLetterContent) {
		return CoverLetterContentResponseDto.builder()
				.id(coverLetterContent.getId())
				.item(coverLetterContent.getItem())
				.content(coverLetterContent.getContent())
				.build();
	}

}
