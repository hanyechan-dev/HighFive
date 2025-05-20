package com.jobPrize.service.member.languageTest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.entity.member.LanguageTest;
import com.jobPrize.entity.member.Member;
import com.jobPrize.memberService.dto.languageTest.LanguageTestCreateDto;
import com.jobPrize.memberService.dto.languageTest.LanguageTestResponseDto;
import com.jobPrize.memberService.dto.languageTest.LanguageTestUpdateDto;
import com.jobPrize.repository.member.languageTest.LanguageTestRepository;
import com.jobPrize.repository.member.member.MemberRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
@Transactional
public class LanguageTestServiceImpl implements LanguageTestService {

	private final LanguageTestRepository languageTestRepository;

	private final MemberRepository memberRepository;

	@Override
	public void createLanguageTest(Long id, LanguageTestCreateDto languageTestCreateDto) {
		Member member = memberRepository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException("존재하지 않는 회원입니다."));
		
		LanguageTest languageTest = LanguageTest.of(member, languageTestCreateDto);
		
		languageTestRepository.save(languageTest);
		
	}

	@Override
	public List<LanguageTestResponseDto> getLanguageTestList(Long id) {

		List<LanguageTest> languageTests = languageTestRepository.findAllByMemberId(id);
		List<LanguageTestResponseDto> languageTestResponseDtos = new ArrayList<>();

		for (LanguageTest languageTest : languageTests) {
			languageTestResponseDtos.add(LanguageTestResponseDto.from(languageTest));
		}
		return languageTestResponseDtos;
	}

	@Override
	public void updateLanguageTest(Long id, LanguageTestUpdateDto languageTestUpdateDto) {
		Long languageTestId = languageTestUpdateDto.getId();
		LanguageTest languageTest = languageTestRepository.findById(languageTestId)
			.orElseThrow(() -> new EntityNotFoundException("존재하지 않는 자격증입니다."));
		
		if(!languageTest.getMember().getId().equals(id)) {
			throw new AccessDeniedException("자격증의 대상과 회원이 일치하지 않습니다.");
		}
		
		languageTest.updateLanguageTest(languageTestUpdateDto);
		
	}

	@Override
	public void deleteLanguageTest(Long id, Long languageTestId) {
		
		LanguageTest languageTest = languageTestRepository.findById(languageTestId)
			.orElseThrow(() -> new EntityNotFoundException("존재하지 않는 자격증입니다."));
		
		if(!languageTest.getMember().getId().equals(id)) {
			throw new AccessDeniedException("자격증의 대상과 회원이 일치하지 않습니다.");
		}
		
		languageTestRepository.delete(languageTest);
		
	}

}
