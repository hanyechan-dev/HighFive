package com.jobPrize.dto.admin.setting;

import java.util.List;

import com.jobPrize.dto.admin.editPrompt.EditPromptResponseDto;
import com.jobPrize.dto.admin.editPrompt.EditPromptSummaryDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EditPromptSettingResponseDto {
	
	private EditPromptResponseDto applied;
	
	private List<EditPromptSummaryDto> list;
	
}
