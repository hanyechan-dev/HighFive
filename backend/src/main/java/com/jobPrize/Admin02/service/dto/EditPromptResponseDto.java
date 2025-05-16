package com.jobPrize.Admin02.service.dto;

import com.jobPrize.entity.admin.EditPrompt;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EditPromptResponseDto {
	private Long id;
	private String title;
	private String content;
	private boolean isApplied;
	
	public static EditPromptResponseDto from(EditPrompt editPrompt) {
		return EditPromptResponseDto
				.builder()
				.id(editPrompt.getId())
				.title(editPrompt.getTitle())
				.content(editPrompt.getContent())
				.isApplied(editPrompt.isApplied())
				.build();
						
	}

}
