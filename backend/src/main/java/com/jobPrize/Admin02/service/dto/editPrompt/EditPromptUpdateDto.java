package com.jobPrize.Admin02.service.dto.editPrompt;

import com.jobPrize.entity.admin.EditPrompt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EditPromptUpdateDto {
	private String title;
	private String content;
	
	public static EditPromptUpdateDto from(EditPrompt editPrompt) {
		return EditPromptUpdateDto
				.builder()
				.title(editPrompt.getTitle())
				.content(editPrompt.getContent())
				.build();
	}

}
