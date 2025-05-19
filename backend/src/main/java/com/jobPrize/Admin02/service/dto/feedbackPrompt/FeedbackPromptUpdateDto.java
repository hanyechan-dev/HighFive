package com.jobPrize.Admin02.service.dto.feedbackPrompt;

import com.jobPrize.entity.admin.FeedbackPrompt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeedbackPromptUpdateDto {
	private String title;
	private String content;
	
	public static FeedbackPromptUpdateDto from(FeedbackPrompt feedbackPrompt) {
		return FeedbackPromptUpdateDto
				.builder()
				.title(feedbackPrompt.getTitle())
				.content(feedbackPrompt.getContent())
				.build();
				
	}

}
