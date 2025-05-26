package com.jobPrize.service.admin.editPrompt;

import java.util.List;

import com.jobPrize.dto.admin.editPrompt.EditPromptCreateDto;
import com.jobPrize.dto.admin.editPrompt.EditPromptResponseDto;
import com.jobPrize.dto.admin.editPrompt.EditPromptSummaryDto;
import com.jobPrize.dto.admin.editPrompt.EditPromptUpdateDto;
import com.jobPrize.entity.common.UserType;

public interface EditPromptService {
	
	void createEditPrompt(UserType userType, EditPromptCreateDto dto);
	
	void updateEditPrompt(UserType userType, EditPromptUpdateDto dto);
	
	List<EditPromptSummaryDto> readAllList();
	
	EditPromptResponseDto readEditPromptById(Long editPromptId);
	
	void applyEditPrompt(Long editPromptId);      
	
	void unApplyEditPrompt();     
	
	EditPromptResponseDto readAppliedEditPrompt();
	
	void deleteEditPrompt(Long editPromptId);



}
