package com.jobPrize.Admin02.service.dto;

import com.jobPrize.entity.admin.EditPrompt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EditPromptRequestDto {
	private String title;
	private String content;
	
	public static EditPromptRequestDto form(EditPrompt editPrompt) {
		return EditPromptRequestDto
				.builder()
				.title(editPrompt.getTitle())
				.content(editPrompt.getContent())
				.build();
	}

}
