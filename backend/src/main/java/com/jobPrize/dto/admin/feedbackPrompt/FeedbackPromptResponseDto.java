package com.jobPrize.dto.admin.feedbackPrompt;

import com.jobPrize.entity.admin.FeedbackPrompt;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FeedbackPromptResponseDto {
	
	private Long id;
	private String title;
	private String content;
	private boolean isApplied;
	
	public static FeedbackPromptResponseDto from(FeedbackPrompt feedbackPrompt) {
		return FeedbackPromptResponseDto
				.builder()
				.id(feedbackPrompt.getId())
				.title(feedbackPrompt.getTitle())
				.content(feedbackPrompt.getContent())
				.isApplied(feedbackPrompt.isApplied())
				.build();
	}

}
