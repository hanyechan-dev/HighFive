package com.jobPrize.dto.admin.feedbackPrompt;

import com.jobPrize.entity.admin.FeedbackPrompt;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FeedbackPromptSummaryDto {
	
	private Long id;
	private String title;
	private boolean isApplied;
	
	public static FeedbackPromptSummaryDto from(FeedbackPrompt feedbackPrompt) {
		return FeedbackPromptSummaryDto
				.builder()
				.id(feedbackPrompt.getId())
				.title(feedbackPrompt.getTitle())
				.isApplied(feedbackPrompt.isApplied())
				.build();
	}

}
