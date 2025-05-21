package com.jobPrize.dto.admin.feedbackPrompt;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class FeedbackPromptUpdateDto {
	
	@NotNull(message = "수정시 id는 필수입니다.")
	private Long id;
	
	@NotBlank(message = "제목은 필수로 입력해야 합니다.")
	@Size(max = 255, message = "제목은 255자 이하로 입력해야 합니다.")
	private String title;
	
	@NotBlank(message = "내용은 필수로 입력해야 합니다.")
	@Size(max = 3000, message = "내용은 3000자 이하로 입력해야 합니다")
	private String content;
	


}
