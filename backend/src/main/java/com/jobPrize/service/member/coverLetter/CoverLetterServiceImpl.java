package com.jobPrize.service.member.coverLetter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.customException.CustomEntityNotFoundException;
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
import com.jobPrize.enumerate.UserType;
import com.jobPrize.repository.member.coverLetter.CoverLetterRepository;
import com.jobPrize.repository.member.member.MemberRepository;
import com.jobPrize.service.member.coverLetterContent.CoverLetterContentService;
import com.jobPrize.util.AssertUtil;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CoverLetterServiceImpl implements CoverLetterService{

	private final CoverLetterRepository coverLetterRepository;

	private final MemberRepository memberRepository;

	private final CoverLetterContentService coverLetterContentService;

	private final AssertUtil assertUtil;

	private final static String ENTITY_NAME = "자기소개서";

	private final static UserType ALLOWED_USER_TYPE = UserType.일반회원;
	
	@Override
	public CoverLetterResponseDto createCoverLetter(Long id, UserType userType, CoverLetterCreateDto coverLetterCreateDto) {

		String action = "등록";
		
		assertUtil.assertUserType(userType, ALLOWED_USER_TYPE, ENTITY_NAME, action);
		
		Member member = memberRepository.findByIdAndDeletedDateIsNull(id)
			.orElseThrow(() -> new CustomEntityNotFoundException("회원"));

		CoverLetter coverLetter = CoverLetter.of(member, coverLetterCreateDto);
		coverLetterRepository.save(coverLetter);

		List<CoverLetterContentCreateDto> coverLetterContentCreateDtos = coverLetterCreateDto.getContents();
		List<CoverLetterContentResponseDto> coverLetterContentResponseDtos = new ArrayList<>();
		for(CoverLetterContentCreateDto coverLetterContentCreateDto : coverLetterContentCreateDtos) {
			CoverLetterContentResponseDto coverLetterContentResponseDto = coverLetterContentService.createCoverLetterContent(coverLetter, coverLetterContentCreateDto);
			coverLetterContentResponseDtos.add(coverLetterContentResponseDto);
		}
		
		return CoverLetterResponseDto.of(coverLetter, coverLetterContentResponseDtos);
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

		String action = "조회";
		
		CoverLetter coverLetter =coverLetterRepository.findWithCoverLetterContentsByCoverLetterId(coverLetterId)
				.orElseThrow(() -> new CustomEntityNotFoundException(ENTITY_NAME));

		Long memberId = coverLetterRepository.findMemberIdByCoverLetterId(coverLetterId)
				.orElseThrow(() -> new CustomEntityNotFoundException("회원"));
		
		assertUtil.assertId(id, memberId, ENTITY_NAME, action);
		
		List<CoverLetterContent> coverLetterContents = coverLetter.getCoverLetterContents();
		List<CoverLetterContentResponseDto> coverLetterContentResponseDtos = new ArrayList<>();
		
		for(CoverLetterContent coverLetterContent: coverLetterContents) {
			coverLetterContentResponseDtos.add(CoverLetterContentResponseDto.from(coverLetterContent));
		}
		
		return CoverLetterResponseDto.of(coverLetter,coverLetterContentResponseDtos );
	}

	@Override
	public CoverLetterResponseDto updateCoverLetter(Long id, CoverLetterUpdateDto coverLetterUpdateDto) {

		String action = "수정";

		CoverLetter coverLetter = coverLetterRepository.findWithCoverLetterContentsByCoverLetterId(coverLetterUpdateDto.getId())
			.orElseThrow(() -> new CustomEntityNotFoundException(ENTITY_NAME));

		Long memberId = coverLetterRepository.findMemberIdByCoverLetterId(coverLetterUpdateDto.getId())
			.orElseThrow(() -> new CustomEntityNotFoundException("회원"));
		
		assertUtil.assertId(id, memberId, ENTITY_NAME, action);
		
		coverLetter.updateCoverLetter(coverLetterUpdateDto);
		List<CoverLetterContentUpdateDto> coverLetterContentUpdateDtos = coverLetterUpdateDto.getContents();
		List<CoverLetterContentResponseDto> coverLetterContentResponseDtos = new ArrayList<>();
		for(CoverLetterContentUpdateDto coverLetterContentUpdateDto : coverLetterContentUpdateDtos) {
			CoverLetterContentResponseDto coverLetterContentResponseDto = coverLetterContentService.updateCoverLetterContent(coverLetterContentUpdateDto);
			coverLetterContentResponseDtos.add(coverLetterContentResponseDto);
		}
		
		
		return CoverLetterResponseDto.of(coverLetter, coverLetterContentResponseDtos);
	}

	@Override
	public void deleteCoverLetter(Long id, Long coverLetterId) {

		String action = "삭제";

		CoverLetter coverLetter = coverLetterRepository.findWithCoverLetterContentsByCoverLetterId(coverLetterId)
			.orElseThrow(() -> new CustomEntityNotFoundException(ENTITY_NAME));
		
		Long memberId = coverLetterRepository.findMemberIdByCoverLetterId(coverLetterId)
			.orElseThrow(() -> new CustomEntityNotFoundException("회원"));
		
		assertUtil.assertId(id, memberId, ENTITY_NAME, action);

		coverLetterRepository.delete(coverLetter);
		
	}

}
