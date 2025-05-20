package com.jobPrize.Admin02.service.service.feedbackPrompt;

import java.util.List;

import com.jobPrize.Admin02.service.dto.feedbackPrompt.FeedbackPromptCreateDto;
import com.jobPrize.Admin02.service.dto.feedbackPrompt.FeedbackPromptResponseDto;
import com.jobPrize.Admin02.service.dto.feedbackPrompt.FeedbackPromptUpdateDto;



public interface FeedbackPromptService {
	
void feedbackPromptCreate(FeedbackPromptCreateDto dto, String token);
	
	void feedbackPromptUpdate(Long id, FeedbackPromptUpdateDto dto, String token);
	
	
	List<FeedbackPromptResponseDto> getAll();
	
	FeedbackPromptResponseDto getById(Long Id);
	
	void feedbackPromptApplyChange(Long Id);      
	
	void feedbackPromptCancelApplied();     

}
