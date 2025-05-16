package com.jobPrize.Admin02.service.dto;

import com.jobPrize.entity.admin.FeedbackPrompt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeedbackPromptRequestDto {

	private String title;
	private String content;
	
	public static FeedbackPromptRequestDto from (FeedbackPrompt feedbackPrompt) {
		return FeedbackPromptRequestDto
				.builder()
				.title(feedbackPrompt.getTitle())
				.content(feedbackPrompt.getContent())
				.build();
	}
}
