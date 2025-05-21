package com.jobPrize.service.member.coverLetter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.dto.member.coverLetter.CoverLetterContentCreateDto;
import com.jobPrize.dto.member.coverLetter.CoverLetterContentResponseDto;
import com.jobPrize.dto.member.coverLetter.CoverLetterContentUpdateDto;
import com.jobPrize.dto.member.coverLetter.CoverLetterCreateDto;
import com.jobPrize.dto.member.coverLetter.CoverLetterResponseDto;
import com.jobPrize.dto.member.coverLetter.CoverLetterSummaryDto;
import com.jobPrize.dto.member.coverLetter.CoverLetterUpdateDto;
import com.jobPrize.entity.member.CoverLetter;
import com.jobPrize.entity.member.CoverLetterContent;
import com.jobPrize.entity.member.Member;
import com.jobPrize.repository.member.coverLetter.CoverLetterRepository;
import com.jobPrize.repository.member.member.MemberRepository;
import com.jobPrize.service.member.coverLetterContent.CoverLetterContentService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CoverLetterServiceImpl implements CoverLetterService{


	private final CoverLetterRepository coverLetterRepository;
	private final MemberRepository memberRepository;
	private final CoverLetterContentService coverLetterContentService;

	
	
	
	@Override
	public void createCoverLetter(Long id, CoverLetterCreateDto coverLetterCreateDto) {
		Member member = memberRepository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException("존재하지 않는 회원입니다."));

		CoverLetter coverLetter = CoverLetter.of(member, coverLetterCreateDto);
		coverLetterRepository.save(coverLetter);

		List<CoverLetterContentCreateDto> coverLetterContentCreateDtos = coverLetterCreateDto.getContents();
		
		for(CoverLetterContentCreateDto coverLetterContentCreateDto : coverLetterContentCreateDtos) {
			
			coverLetterContentService.createCoverLetterContent(coverLetter, coverLetterContentCreateDto);

		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<CoverLetterSummaryDto> readCoverLetterList(Long id) {
		
		List<CoverLetter> coverLetters = coverLetterRepository.findAllByMemberId(id);
		List<CoverLetterSummaryDto> coverLetterSummaryDtos = new ArrayList<>();
		for(CoverLetter coverLetter : coverLetters) {
			coverLetterSummaryDtos.add(CoverLetterSummaryDto.from(coverLetter));
		}
		
		
		return coverLetterSummaryDtos;
	}

	@Override
	@Transactional(readOnly = true)
	public CoverLetterResponseDto readCoverLetter(Long id, Long coverLetterId) {
		CoverLetter coverLetter =coverLetterRepository.findWithCoverLetterContentsByCoverLetterId(coverLetterId)
				.orElseThrow(() -> new EntityNotFoundException("존재하지 않는 자기소개서입니다."));
		
		if(!coverLetter.getMember().getId().equals(id)) {
			throw new AccessDeniedException("해당 자기소개서를 조회할 수 없습니다.");
		}
		
		List<CoverLetterContent> coverLetterContents = coverLetter.getCoverLetterContents();
		List<CoverLetterContentResponseDto> coverLetterContentResponseDtos = new ArrayList<>();
		
		for(CoverLetterContent coverLetterContent: coverLetterContents) {
			coverLetterContentResponseDtos.add(CoverLetterContentResponseDto.from(coverLetterContent));
		}
		
		return CoverLetterResponseDto.of(coverLetter,coverLetterContentResponseDtos );
	}

	@Override
	public void updateCoverLetter(Long id, CoverLetterUpdateDto coverLetterUpdateDto) {

		CoverLetter coverLetter = coverLetterRepository.findWithCoverLetterContentsByCoverLetterId(coverLetterUpdateDto.getId())
			.orElseThrow(() -> new EntityNotFoundException("존재하지 않는 자기소개서입니다."));
		
		if(!coverLetter.getMember().getId().equals(id)) {
			throw new AccessDeniedException("해당 자기소개서를 수정할 수 없습니다.");
		}
		
		coverLetter.updateCoverLetter(coverLetterUpdateDto);
		List<CoverLetterContentUpdateDto> coverLetterContentUpdateDtos = coverLetterUpdateDto.getContents();
		for(CoverLetterContentUpdateDto coverLetterContentUpdateDto : coverLetterContentUpdateDtos) {
			coverLetterContentService.updateCoverLetterContent(coverLetterContentUpdateDto);
		}
		
		
	}

	@Override
	public void deleteCoverLetter(Long id, Long coverLetterId) {

		CoverLetter coverLetter = coverLetterRepository.findWithCoverLetterContentsByCoverLetterId(coverLetterId)
			.orElseThrow(() -> new EntityNotFoundException("존재하지 않는 자기소개서입니다."));
		
		if(!coverLetter.getMember().getId().equals(id)) {
			throw new AccessDeniedException("해당 자기소개서를 삭제할 수 없습니다.");
		}
		
		List<CoverLetterContent> coverLetterContents = coverLetter.getCoverLetterContents();
		for(CoverLetterContent coverLetterContent : coverLetterContents ) {
			coverLetterContentService.deleteCoverLetterContent(coverLetterContent.getId());
		}
		

		coverLetterRepository.delete(coverLetter);
		
	}

}
