package com.jobPrize.dto.common.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class PostCreateDto {
		
	
	@NotBlank(message = "제목은 필수로 입력해야 합니다.")
	@Size(max = 50, message = "제목은 50자 이하로 입력해야 합니다.")
	private String title;
	
	@NotBlank(message = "내용은 필수로 입력해야 합니다.")
	@Size(max = 500, message = "내용은 500자 이하로 입력해야 합니다")
	private String content;

}
