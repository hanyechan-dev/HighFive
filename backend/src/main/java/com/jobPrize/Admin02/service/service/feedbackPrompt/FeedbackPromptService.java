package com.jobPrize.Admin02.service.service.feedbackPrompt;

import java.util.List;

import com.jobPrize.Admin02.service.dto.feedbackPrompt.FeedbackPromptCreateDto;
import com.jobPrize.Admin02.service.dto.feedbackPrompt.FeedbackPromptResponseDto;
import com.jobPrize.Admin02.service.dto.feedbackPrompt.FeedbackPromptUpdateDto;
import com.jobPrize.entity.common.UserType;

public interface FeedbackPromptService {

	void createFeedbackPrompt(UserType userType, FeedbackPromptCreateDto dto);

	void updateFeedbackPrompt(UserType userType, FeedbackPromptUpdateDto dto );

	List<FeedbackPromptResponseDto> readAllList();

	FeedbackPromptResponseDto readFeedbackPromptById(FeedbackPromptResponseDto dto);

	void applyChangedFeedbackPrompt(Long feedbackPromptId);

	void cancelChangedFeedbackPrompt();

}
