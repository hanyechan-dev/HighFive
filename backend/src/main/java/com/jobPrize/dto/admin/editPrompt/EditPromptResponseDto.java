package com.jobPrize.dto.admin.editPrompt;

import com.jobPrize.entity.admin.EditPrompt;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EditPromptResponseDto {
	private Long id;
	private String title;
	private String content;
	
	public static EditPromptResponseDto from(EditPrompt editPrompt) {
		return EditPromptResponseDto
				.builder()
				.id(editPrompt.getId())
				.title(editPrompt.getTitle())
				.content(editPrompt.getContent())
				.build();
						
	}

}
