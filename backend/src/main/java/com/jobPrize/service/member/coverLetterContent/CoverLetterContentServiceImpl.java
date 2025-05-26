package com.jobPrize.service.member.coverLetterContent;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.customException.CustomEntityNotFoundException;
import com.jobPrize.dto.member.coverLetter.CoverLetterContentCreateDto;
import com.jobPrize.dto.member.coverLetter.CoverLetterContentUpdateDto;
import com.jobPrize.entity.member.CoverLetter;
import com.jobPrize.entity.member.CoverLetterContent;
import com.jobPrize.repository.member.coverLetterContent.CoverLetterContentRepository;

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
				.orElseThrow(() -> new CustomEntityNotFoundException("자기소개서 내용"));
		coverLetterContent.updateContent(coverLetterContentUpdateDto);
		
	}

	@Override
	public void deleteCoverLetterContent(Long coverLetterContentId) {
		CoverLetterContent coverLetterContent = coverLetterContentRepository.findById(coverLetterContentId)
				.orElseThrow(() -> new CustomEntityNotFoundException("자기소개서 내용"));
		coverLetterContentRepository.delete(coverLetterContent);
		
	}



}
