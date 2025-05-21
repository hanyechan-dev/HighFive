package com.jobPrize.Admin02.service.dto.editPrompt;

import com.jobPrize.entity.admin.EditPrompt;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EditPrompCreateDto {
	
	@NotBlank(message = "제목은 필수로 입력해야 합니다.")
	@Size(max = 255, message = "제목은 255자 이하로 입력해야 합니다.")
	private String title;
	
	@NotBlank(message = "내용은 필수로 입력해야 합니다.")
	@Size(max = 3000, message = "내용은 3000자 이하로 입력해야 합니다")
	private String content;
	
	public static EditPrompCreateDto form(EditPrompt editPrompt) {
		return EditPrompCreateDto
				.builder()
				.title(editPrompt.getTitle())
				.content(editPrompt.getContent())
				.build();
	}

}
