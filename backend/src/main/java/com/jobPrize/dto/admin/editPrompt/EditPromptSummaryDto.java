package com.jobPrize.dto.admin.editPrompt;

import com.jobPrize.entity.admin.EditPrompt;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EditPromptSummaryDto {
	private Long id;
	private String title;
	private boolean isApplied;
	
	public static EditPromptSummaryDto from(EditPrompt editPrompt) {
		return EditPromptSummaryDto
				.builder()
				.id(editPrompt.getId())
				.title(editPrompt.getTitle())
				.isApplied(editPrompt.isApplied())
				.build();
						
	}

}
