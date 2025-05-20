package com.jobPrize.service.member.coverLetterContent;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.entity.member.CoverLetter;
import com.jobPrize.entity.member.CoverLetterContent;
import com.jobPrize.memberService.dto.coverLetter.CoverLetterContentCreateDto;
import com.jobPrize.memberService.dto.coverLetter.CoverLetterContentUpdateDto;
import com.jobPrize.repository.member.coverLetterContent.CoverLetterContentRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;


@Service
@Transactional
@RequiredArgsConstructor
public class CoverLetterContentServiceImpl implements CoverLetterContentService {

	private final CoverLetterContentRepository coverLetterContentRepository;

	@Override
	public void createCoverLetterContent(CoverLetter coverLetter, CoverLetterContentCreateDto coverLetterContentCreateDto) {
		CoverLetterContent coverLetterContent = CoverLetterContent.of(coverLetter, coverLetterContentCreateDto);
		coverLetterContentRepository.save(coverLetterContent);
	}


	@Override
	public void updateCoverLetterContent(CoverLetterContentUpdateDto coverLetterContentUpdateDto) {
		CoverLetterContent coverLetterContent = coverLetterContentRepository.findById(coverLetterContentUpdateDto.getId())
			.orElseThrow(() -> new EntityNotFoundException("존재하지 않는 자기소개서 내용입니다."));
		coverLetterContent.updateContent(coverLetterContentUpdateDto);
		
	}

	@Override
	public void deleteCoverLetterContent(Long coverLetterContentId) {
		CoverLetterContent coverLetterContent = coverLetterContentRepository.findById(coverLetterContentId)
			.orElseThrow(() -> new EntityNotFoundException("존재하지 않는 자기소개서 내용입니다."));
		coverLetterContentRepository.delete(coverLetterContent);
		
	}



}
