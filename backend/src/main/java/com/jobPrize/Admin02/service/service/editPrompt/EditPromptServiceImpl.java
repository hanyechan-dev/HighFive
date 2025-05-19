package com.jobPrize.Admin02.service.service.editPrompt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.Admin02.service.dto.editPrompt.EditPrompCreateDto;
import com.jobPrize.Admin02.service.dto.editPrompt.EditPromptResponseDto;
import com.jobPrize.Admin02.service.dto.editPrompt.EditPromptUpdateDto;
import com.jobPrize.entity.admin.EditPrompt;
import com.jobPrize.entity.common.UserType;
import com.jobPrize.jwt.TokenProvider;
import com.jobPrize.repository.admin.editPrompt.EditPromptRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EditPromptServiceImpl implements EditPromptService {
	private final EditPromptRepository editPromptRepository;
	private final TokenProvider tokenProvider;

	@Override
	@Transactional
	public void editPromptCreate(EditPrompCreateDto dto, String token) {
		UserType userType = tokenProvider.getUserTypeFromToken(token);
		if(userType != UserType.관리자) {
			throw new IllegalArgumentException("관리자만 작성할 수 있습니다");
		}
		EditPrompt prompt = EditPrompt
				.builder()
				.title(dto.getTitle())
				.content(dto.getContent())
				.isApplied(false)
				.build();
		editPromptRepository.save(prompt);
	}
	
	@Override
	@Transactional
	public void editPromptUpdate(Long id, EditPromptUpdateDto dto, String token) {
		UserType userType = tokenProvider.getUserTypeFromToken(token);
		EditPrompt editPrompt = editPromptRepository.findById(id)
			    .orElseThrow(() -> new IllegalArgumentException("해당 프롬프트가 존재하지 않습니다."));

		if(userType !=UserType.관리자) {
			throw new IllegalArgumentException("관리자만 수정할 수 있습니다.");
		}
		editPrompt.updateEditPrompt(dto.getTitle(), dto.getContent());
	}

	@Override
	@Transactional(readOnly = true)
	public List<EditPromptResponseDto> getAll() {
		List<EditPrompt> editPrompts = editPromptRepository.findAll();
		List<EditPromptResponseDto> results = new ArrayList<>();

		for (EditPrompt editPrompt : editPrompts) {
			EditPromptResponseDto dto = EditPromptResponseDto.from(editPrompt);
			results.add(dto);
		}
		return results;
		
		
	}
	@Override
	@Transactional(readOnly = true)
	public EditPromptResponseDto getById(Long Id) {
		EditPrompt prompt = editPromptRepository.findById(Id)
		        .orElseThrow(() -> new IllegalArgumentException("해당 ID의 프롬프트가 없습니다: " + Id));
		return EditPromptResponseDto.from(prompt);
	
	}
	
	@Override
	@Transactional
	public void EditPromptapplyChange(Long Id) {
		EditPromptcancelApplied();
		 EditPrompt editPrompt = editPromptRepository.findById(Id)
				 .orElseThrow(() -> new IllegalArgumentException("해당 프롬프트가 존재하지 않습니다"));
		 editPrompt.apply();
	}
	
	@Override
	@Transactional
	public void EditPromptcancelApplied() {
		 Optional<EditPrompt> optional = editPromptRepository.findAppliedPrompt();
		 optional.ifPresent(editPrompt -> {
		        editPrompt.unApply(); 
		    });
	}

}
