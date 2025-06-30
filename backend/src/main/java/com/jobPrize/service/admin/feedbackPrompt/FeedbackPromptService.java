package com.jobPrize.service.admin.feedbackPrompt;

import java.util.List;

import com.jobPrize.dto.admin.feedbackPrompt.FeedbackPromptCreateDto;
import com.jobPrize.dto.admin.feedbackPrompt.FeedbackPromptResponseDto;
import com.jobPrize.dto.admin.feedbackPrompt.FeedbackPromptSummaryDto;
import com.jobPrize.dto.admin.feedbackPrompt.FeedbackPromptUpdateDto;
import com.jobPrize.enumerate.UserType;

public interface FeedbackPromptService {

	FeedbackPromptResponseDto createFeedbackPrompt(UserType userType, FeedbackPromptCreateDto dto);

	void updateFeedbackPrompt(UserType userType, FeedbackPromptUpdateDto dto );

	List<FeedbackPromptSummaryDto> readAllList(UserType userType);

	FeedbackPromptResponseDto readFeedbackPrompt(UserType userType, Long feedbackPromptId);

	void applyFeedbackPrompt(UserType userType, Long feedbackPromptId);
	
	FeedbackPromptResponseDto readAppliedFeedbackPrompt(UserType userType);
	
	void deleteFeedbackPrompt(UserType userType, Long feedbackPromptId);


}
