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
public class EditPrompCreateDto {
	private String title;
	private String content;
	
	public static EditPrompCreateDto form(EditPrompt editPrompt) {
		return EditPrompCreateDto
				.builder()
				.title(editPrompt.getTitle())
				.content(editPrompt.getContent())
				.build();
	}

}
