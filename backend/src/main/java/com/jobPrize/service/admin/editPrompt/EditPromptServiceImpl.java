package com.jobPrize.service.admin.editPrompt;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.customException.CustomEntityNotFoundException;
import com.jobPrize.dto.admin.editPrompt.EditPrompCreateDto;
import com.jobPrize.dto.admin.editPrompt.EditPromptResponseDto;
import com.jobPrize.dto.admin.editPrompt.EditPromptSummaryDto;
import com.jobPrize.dto.admin.editPrompt.EditPromptUpdateDto;
import com.jobPrize.entity.admin.EditPrompt;
import com.jobPrize.entity.common.UserType;
import com.jobPrize.repository.admin.editPrompt.EditPromptRepository;
import com.jobPrize.util.AssertUtil;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class EditPromptServiceImpl implements EditPromptService {
	
	private final EditPromptRepository editPromptRepository;
	
	private final AssertUtil assertUtil;

	@Override
	public void createEditPrompt(UserType userType, EditPrompCreateDto dto) {
		
		assertUtil.assertUserType(userType, UserType.관리자, "작성");
		
		EditPrompt prompt = EditPrompt.createFrom(dto);
		
		editPromptRepository.save(prompt);
	}
	
	@Override
	public void updateEditPrompt(UserType userType, EditPromptUpdateDto dto) {
		EditPrompt editPrompt = editPromptRepository.findById(dto.getId())
			    .orElseThrow(() -> new CustomEntityNotFoundException("프롬프트"));

		assertUtil.assertUserType(userType, UserType.관리자, "수정");
		
		editPrompt.updateEditPrompt(dto.getTitle(), dto.getContent());
	}

	@Override
	@Transactional(readOnly = true)
	public List<EditPromptSummaryDto> readAllList() {
		List<EditPrompt> editPrompts = editPromptRepository.findAll();
		List<EditPromptSummaryDto> results = new ArrayList<>();

		for (EditPrompt editPrompt : editPrompts) {
			EditPromptSummaryDto dto = EditPromptSummaryDto.from(editPrompt);
			results.add(dto);
		}
		return results;
		
		
	}
	@Override
	@Transactional(readOnly = true)
	public EditPromptResponseDto readEditPrompt(Long editPromptId) {
		EditPrompt prompt = editPromptRepository.findById(editPromptId)
				.orElseThrow(() -> new CustomEntityNotFoundException("프롬프트"));
		return EditPromptResponseDto.from(prompt);
	
	}
	
	@Override
	public void applyEditPrompt(Long editPromptId) {
		 unApplyEditPrompt();
		 EditPrompt editPrompt = editPromptRepository.findById(editPromptId)
				 .orElseThrow(() -> new CustomEntityNotFoundException("프롬프트"));
		 editPrompt.apply();
	}

	private void  unApplyEditPrompt() {
		editPromptRepository.findAppliedPrompt()
        	.ifPresent(editPrompt -> editPrompt.unApply());
	}



}
