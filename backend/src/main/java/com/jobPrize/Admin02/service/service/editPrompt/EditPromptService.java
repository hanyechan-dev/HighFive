package com.jobPrize.Admin02.service.service.editPrompt;

import java.util.List;

import com.jobPrize.Admin02.service.dto.editPrompt.EditPrompCreateDto;
import com.jobPrize.Admin02.service.dto.editPrompt.EditPromptResponseDto;
import com.jobPrize.Admin02.service.dto.editPrompt.EditPromptUpdateDto;

public interface EditPromptService {
	
	void editPromptCreate(EditPrompCreateDto dto, String token);
	
	void editPromptUpdate(Long id, EditPromptUpdateDto dto, String token);
	
	
	List<EditPromptResponseDto> getAll();
	
	EditPromptResponseDto getById(Long Id);
	
	void EditPromptapplyChange(Long Id);      
	
	void EditPromptcancelApplied();     



}
