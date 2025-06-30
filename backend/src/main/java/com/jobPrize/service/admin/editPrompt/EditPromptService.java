package com.jobPrize.service.admin.editPrompt;

import java.util.List;

import com.jobPrize.dto.admin.editPrompt.EditPromptCreateDto;
import com.jobPrize.dto.admin.editPrompt.EditPromptResponseDto;
import com.jobPrize.dto.admin.editPrompt.EditPromptSummaryDto;
import com.jobPrize.dto.admin.editPrompt.EditPromptUpdateDto;
import com.jobPrize.enumerate.UserType;

public interface EditPromptService {
	
	EditPromptResponseDto createEditPrompt(UserType userType, EditPromptCreateDto dto);
	
	void updateEditPrompt(UserType userType, EditPromptUpdateDto dto);
	
	List<EditPromptSummaryDto> readAllList(UserType userType);

	EditPromptResponseDto readEditPrompt(UserType userType, Long editPromptId);
	
	void applyEditPrompt(UserType userType, Long editPromptId);
	
	EditPromptResponseDto readAppliedEditPrompt(UserType userType);
	
	void deleteEditPrompt(UserType userType, Long editPromptId);

}
