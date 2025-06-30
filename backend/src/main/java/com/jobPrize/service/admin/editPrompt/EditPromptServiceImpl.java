package com.jobPrize.service.admin.editPrompt;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.customException.CustomEntityNotFoundException;
import com.jobPrize.dto.admin.editPrompt.EditPromptCreateDto;
import com.jobPrize.dto.admin.editPrompt.EditPromptResponseDto;
import com.jobPrize.dto.admin.editPrompt.EditPromptSummaryDto;
import com.jobPrize.dto.admin.editPrompt.EditPromptUpdateDto;
import com.jobPrize.entity.admin.EditPrompt;
import com.jobPrize.enumerate.UserType;
import com.jobPrize.repository.admin.editPrompt.EditPromptRepository;
import com.jobPrize.util.AssertUtil;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class EditPromptServiceImpl implements EditPromptService {

	private final EditPromptRepository editPromptRepository;

	private final AssertUtil assertUtil;

	private final static String ENTITY_NAME = "첨삭 프롬프트";

	private final static UserType ALLOWED_USER_TYPE = UserType.관리자;

	@Override

	public EditPromptResponseDto createEditPrompt(UserType userType, EditPromptCreateDto dto) {

		String action = "작성";

		assertUtil.assertUserType(userType, ALLOWED_USER_TYPE, ENTITY_NAME, action);

		EditPrompt prompt = EditPrompt.createFrom(dto);

		editPromptRepository.save(prompt);
		
		return EditPromptResponseDto.from(prompt);
	}

	@Override
	public void updateEditPrompt(UserType userType, EditPromptUpdateDto dto) {

		String action = "수정";
		assertUtil.assertUserType(userType, ALLOWED_USER_TYPE, ENTITY_NAME, action);

		EditPrompt editPrompt = editPromptRepository.findById(dto.getId())
				.orElseThrow(() -> new CustomEntityNotFoundException(ENTITY_NAME));

		editPrompt.updateEditPrompt(dto.getTitle(), dto.getContent());
	}

	@Override
	@Transactional(readOnly = true)
	public List<EditPromptSummaryDto> readAllList(UserType userType) {

		String action = "조회";

		assertUtil.assertUserType(userType, ALLOWED_USER_TYPE, ENTITY_NAME, action);

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

	public EditPromptResponseDto readEditPrompt(UserType userType, Long editPromptId) {

		String action = "조회";

		assertUtil.assertUserType(userType, ALLOWED_USER_TYPE, ENTITY_NAME, action);

		EditPrompt prompt = editPromptRepository.findById(editPromptId)
				.orElseThrow(() -> new CustomEntityNotFoundException(ENTITY_NAME));

		return EditPromptResponseDto.from(prompt);

	}

	@Override
	public void applyEditPrompt(UserType userType, Long editPromptId) {

		String action = "적용";

		assertUtil.assertUserType(userType, ALLOWED_USER_TYPE, ENTITY_NAME, action);

		unApplyEditPrompt();

		EditPrompt editPrompt = editPromptRepository.findById(editPromptId)
				.orElseThrow(() -> new CustomEntityNotFoundException(ENTITY_NAME));
		editPrompt.apply();
	}

	@Override
	@Transactional(readOnly = true)
	public EditPromptResponseDto readAppliedEditPrompt(UserType userType) {
	    String action = "조회";

	    assertUtil.assertUserType(userType, ALLOWED_USER_TYPE, ENTITY_NAME, action);

	    return editPromptRepository.findAppliedPrompt()
	            .map(EditPromptResponseDto::from)
	            .orElse(null);
	}

	@Override
	public void deleteEditPrompt(UserType userType, Long editPromptId) {

		String action = "삭제";

		assertUtil.assertUserType(userType, ALLOWED_USER_TYPE, ENTITY_NAME, action);

		editPromptRepository.deleteById(editPromptId);

	}

	private void unApplyEditPrompt() {
		editPromptRepository.findAppliedPrompt().ifPresent(editPrompt -> editPrompt.unApply());
	}

}
