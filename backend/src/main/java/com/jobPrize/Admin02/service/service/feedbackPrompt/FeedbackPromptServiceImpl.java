package com.jobPrize.Admin02.service.service.feedbackPrompt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.Admin02.service.dto.feedbackPrompt.FeedbackPromptCreateDto;
import com.jobPrize.Admin02.service.dto.feedbackPrompt.FeedbackPromptResponseDto;
import com.jobPrize.Admin02.service.dto.feedbackPrompt.FeedbackPromptUpdateDto;
import com.jobPrize.entity.admin.FeedbackPrompt;
import com.jobPrize.entity.common.UserType;
import com.jobPrize.jwt.TokenProvider;
import com.jobPrize.repository.admin.feedbackPrompt.FeedbackPromptRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeedbackPromptServiceImpl implements FeedbackPromptService {
	private final FeedbackPromptRepository feedbackPromptRepository;
	private final TokenProvider tokenProvider;

	@Override
	@Transactional
	public void feedbackPromptCreate(FeedbackPromptCreateDto dto, String token) {
	UserType userType = tokenProvider.getUserTypeFromToken(token);
	if(userType != UserType.관리자) {
		throw new IllegalArgumentException("관리자만 작성할 수 있습니다");
	}
	FeedbackPrompt prompt = FeedbackPrompt
			.builder()
			.title(dto.getTitle())
			.content(dto.getContent())
			.isApplied(false)
			.build();
	feedbackPromptRepository.save(prompt);
}

	@Override
	@Transactional
	public void feedbackPromptUpdate(Long id, FeedbackPromptUpdateDto dto, String token) {
		UserType userType = tokenProvider.getUserTypeFromToken(token);
		FeedbackPrompt feedbackPrompt = feedbackPromptRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("해당 프롬프트가 존재하지 않습니다"));
		if (userType != UserType.관리자) {
			throw new IllegalArgumentException("관리자만 수정할 수 있습니다.");
		}
		feedbackPrompt.updateFeedbackPrompt(dto.getTitle(), dto.getContent());

	}

	@Override
	@Transactional(readOnly = true)
	public List<FeedbackPromptResponseDto> getAll() {
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
	public FeedbackPromptResponseDto getById(Long Id) {
		FeedbackPrompt prompt = feedbackPromptRepository.findById(Id)
				.orElseThrow(() -> new IllegalArgumentException("해당 ID의 프롬프트가 없습니다: " + Id));
		return FeedbackPromptResponseDto.from(prompt);
	}

	@Override
	@Transactional
	public void feedbackPromptApplyChange(Long Id) {
		feedbackPromptCancelApplied();
		FeedbackPrompt feedbackPrompt = feedbackPromptRepository.findById(Id)
				.orElseThrow(() -> new IllegalArgumentException("해당 프롬프트가 존재하지 않습니다"));
		feedbackPrompt.apply();
	}

	@Override
	@Transactional
	public void feedbackPromptCancelApplied() {
		Optional<FeedbackPrompt> optional = feedbackPromptRepository.findAppliedPrompt();
		optional.ifPresent(feedbackPrompt -> {
			feedbackPrompt.unApply();
		});
	}
}