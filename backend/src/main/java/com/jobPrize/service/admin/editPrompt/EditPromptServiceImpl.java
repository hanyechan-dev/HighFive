package com.jobPrize.service.admin.editPrompt;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.dto.admin.editPrompt.EditPromptCreateDto;
import com.jobPrize.dto.admin.editPrompt.EditPromptResponseDto;
import com.jobPrize.dto.admin.editPrompt.EditPromptSummaryDto;
import com.jobPrize.dto.admin.editPrompt.EditPromptUpdateDto;
import com.jobPrize.entity.admin.EditPrompt;
import com.jobPrize.entity.common.UserType;
import com.jobPrize.repository.admin.editPrompt.EditPromptRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class EditPromptServiceImpl implements EditPromptService {
	private final EditPromptRepository editPromptRepository;

	@Override
	public void createEditPrompt(UserType userType, EditPromptCreateDto dto) {
		if(userType !=UserType.관리자) {
			throw new AccessDeniedException("관리자만 작성할 수 있습니다.");
		}
		  EditPrompt prompt = EditPrompt.createFrom(dto);
		    editPromptRepository.save(prompt);
	}
	
	@Override
	public void updateEditPrompt(UserType userType, EditPromptUpdateDto dto) {
		EditPrompt editPrompt = editPromptRepository.findById(dto.getId())
			    .orElseThrow(() -> new EntityNotFoundException("해당 프롬프트가 존재하지 않습니다."));

		if(userType !=UserType.관리자) {
			throw new AccessDeniedException("관리자만 작성할 수 있습니다.");
		}
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
	public EditPromptResponseDto readEditPromptById(Long editPromptId) {
		EditPrompt prompt = editPromptRepository.findById(editPromptId)
				.orElseThrow(() -> new EntityNotFoundException("해당 프롬프트가 존재하지 않습니다."));
		return EditPromptResponseDto.from(prompt);
	
	}
	
	@Override
	public void applyEditPrompt(Long editPromptId) {
		 unApplyEditPrompt();
		 EditPrompt editPrompt = editPromptRepository.findById(editPromptId)
				 .orElseThrow(() -> new EntityNotFoundException("해당 프롬프트가 존재하지 않습니다"));
		 editPrompt.apply();
	}
	
	@Override
	public void  unApplyEditPrompt() {
		 EditPrompt editPrompt = editPromptRepository.findAppliedPrompt()
				 .orElseThrow(() -> new EntityNotFoundException("해당 프롬프트가 존재하지 않습니다"));
		 editPrompt.unApply();
	}
	
	@Override
	@Transactional(readOnly = true)
	public EditPromptResponseDto readAppliedEditPrompt(){
		 EditPrompt editPrompt = editPromptRepository.findAppliedPrompt()
			        .orElseThrow(() -> new EntityNotFoundException("현재 '적용중'인 프롬프트가 존재하지 않습니다"));

			    return EditPromptResponseDto.from(editPrompt);

}

	@Override
	public void deleteEditPrompt(Long editPromptId) {
		
		editPromptRepository.deleteById(editPromptId);
		
	}



}
