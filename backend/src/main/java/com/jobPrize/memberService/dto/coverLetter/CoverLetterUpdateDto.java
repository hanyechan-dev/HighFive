package com.jobPrize.memberService.dto.coverLetter;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CoverLetterUpdateDto {
	
	@NotNull(message = "수정 시 id는 필수입니다.")
	private Long id;
	
	@NotBlank(message = "제목은 필수로 입력해야합니다")
	@Size(max = 50, message = "제목은 50자 이하로 입력해야합니다.")
	private String title;
	
	@Valid
	List<CoverLetterContentUpdateDto> contents;
}
