package com.jobPrize.dto.admin.editPrompt;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class EditPrompCreateDto {
	
	@NotBlank(message = "제목은 필수로 입력해야 합니다.")
	@Size(max = 255, message = "제목은 255자 이하로 입력해야 합니다.")
	private String title;
	
	@NotBlank(message = "내용은 필수로 입력해야 합니다.")
	@Size(max = 3000, message = "내용은 3000자 이하로 입력해야 합니다")
	private String content;
	

}
