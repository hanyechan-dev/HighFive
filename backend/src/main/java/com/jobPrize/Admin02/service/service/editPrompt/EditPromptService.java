package com.jobPrize.Admin02.service.service.editPrompt;

import java.util.List;

import com.jobPrize.Admin02.service.dto.editPrompt.EditPrompCreateDto;
import com.jobPrize.Admin02.service.dto.editPrompt.EditPromptResponseDto;
import com.jobPrize.Admin02.service.dto.editPrompt.EditPromptUpdateDto;
import com.jobPrize.entity.common.UserType;

public interface EditPromptService {
	
	void createEditPrompt(UserType userType, EditPrompCreateDto dto);
	
	void updateEditPrompt(UserType userType, EditPromptUpdateDto dto);
	
	
	List<EditPromptResponseDto> readAllList();
	
	EditPromptResponseDto readEditPromptById(EditPromptResponseDto dto);
	
	void applyChangedEditPrompt(Long editPromptId);      
	
	void cancelChangedEditPrompt();     



}
