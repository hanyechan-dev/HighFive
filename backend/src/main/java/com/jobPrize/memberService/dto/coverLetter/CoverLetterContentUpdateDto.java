package com.jobPrize.memberService.dto.coverLetter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CoverLetterContentUpdateDto {
	
	@NotNull(message = "수정 시 id는 필수입니다.")
	private Long id;
	@NotBlank(message = "항목은 필수로 입력해야합니다")
	@Size(max = 100, message = "항목은 100자 이하로 입력해야합니다.")
	private String item;
	@NotBlank(message = "제목은 필수로 입력해야합니다")
	@Size(max = 2000, message = "내용은 2000자 이하로 입력해야합니다.")
	private String content;

}
