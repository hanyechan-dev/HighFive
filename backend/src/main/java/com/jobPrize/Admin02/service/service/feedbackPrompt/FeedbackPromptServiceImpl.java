package com.jobPrize.Admin02.service.service.feedbackPrompt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.Admin02.service.dto.feedbackPrompt.FeedbackPromptCreateDto;
import com.jobPrize.Admin02.service.dto.feedbackPrompt.FeedbackPromptResponseDto;
import com.jobPrize.Admin02.service.dto.feedbackPrompt.FeedbackPromptUpdateDto;
import com.jobPrize.entity.admin.EditPrompt;
import com.jobPrize.entity.admin.FeedbackPrompt;
import com.jobPrize.entity.common.UserType;
import com.jobPrize.jwt.TokenProvider;
import com.jobPrize.repository.admin.feedbackPrompt.FeedbackPromptRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeedbackPromptServiceImpl implements FeedbackPromptService {
	private final FeedbackPromptRepository feedbackPromptRepository;

	@Override
	@Transactional
	public void createFeedbackPrompt(UserType userType, FeedbackPromptCreateDto dto) {
	if(userType != UserType.관리자) {
		throw new IllegalArgumentException("관리자만 작성할 수 있습니다");
	}
	FeedbackPrompt prompt = FeedbackPrompt.createFrom(dto);
	feedbackPromptRepository.save(prompt);
}

	@Override
	@Transactional
	public void updateFeedbackPrompt(UserType userType, FeedbackPromptUpdateDto dto) {
		FeedbackPrompt feedbackPrompt = feedbackPromptRepository.findById(dto.getId())
				.orElseThrow(() -> new IllegalArgumentException("해당 프롬프트가 존재하지 않습니다"));
		if (userType != UserType.관리자) {
			throw new IllegalArgumentException("관리자만 수정할 수 있습니다.");
		}
		feedbackPrompt.updateFeedbackPrompt(dto.getTitle(), dto.getContent());

	}

	@Override
	@Transactional(readOnly = true)
	public List<FeedbackPromptResponseDto> readAllList() {
		List<FeedbackPrompt> feedbackPrompts = feedbackPromptRepository.findAll();
		List<FeedbackPromptResponseDto> results = new ArrayList<>();

		for (FeedbackPrompt feedbackPrompt : feedbackPrompts) {
			FeedbackPromptResponseDto dto = FeedbackPromptResponseDto.from(feedbackPrompt);
			results.add(dto);
		}
		return results;
	}

	@Override
	@Transactional(readOnly = true)
	public FeedbackPromptResponseDto readFeedbackPromptById(FeedbackPromptResponseDto dto) {
		FeedbackPrompt prompt = feedbackPromptRepository.findById(dto.getId())
				.orElseThrow(() -> new IllegalArgumentException("해당 ID의 프롬프트가 없습니다: " + dto.getId()));
		return FeedbackPromptResponseDto.from(prompt);
	}

	@Override
	@Transactional
	public void applyChangedFeedbackPrompt(Long feedbackPromptId) {
		cancelChangedFeedbackPrompt();
		FeedbackPrompt feedbackPrompt = feedbackPromptRepository.findById(feedbackPromptId)
				.orElseThrow(() -> new IllegalArgumentException("해당 프롬프트가 존재하지 않습니다"));
		feedbackPrompt.apply();
	}

	@Override
	@Transactional
	public void cancelChangedFeedbackPrompt() {
		Optional<FeedbackPrompt> optional = feedbackPromptRepository.findAppliedPrompt();
		optional.ifPresent(feedbackPrompt -> {
			feedbackPrompt.unApply();
		});
	}
}