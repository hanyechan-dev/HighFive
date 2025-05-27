package com.jobPrize.dto.admin.setting;

import java.util.List;

import com.jobPrize.dto.admin.feedbackPrompt.FeedbackPromptResponseDto;
import com.jobPrize.dto.admin.feedbackPrompt.FeedbackPromptSummaryDto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FeedbackPromptSettingResponseDto {
	
	private FeedbackPromptResponseDto applied;
	
	private List<FeedbackPromptSummaryDto> list;

}
