package com.jobPrize.service.member.languageTest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.customException.CustomEntityNotFoundException;
import com.jobPrize.dto.member.languageTest.LanguageTestCreateDto;
import com.jobPrize.dto.member.languageTest.LanguageTestResponseDto;
import com.jobPrize.dto.member.languageTest.LanguageTestUpdateDto;
import com.jobPrize.entity.member.LanguageTest;
import com.jobPrize.entity.member.Member;
import com.jobPrize.enumerate.UserType;
import com.jobPrize.repository.member.languageTest.LanguageTestRepository;
import com.jobPrize.repository.member.member.MemberRepository;
import com.jobPrize.util.AssertUtil;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
@Transactional
public class LanguageTestServiceImpl implements LanguageTestService {

	private final LanguageTestRepository languageTestRepository;

	private final MemberRepository memberRepository;

	private final AssertUtil assertUtil;

	@Override
	public void createLanguageTest(Long id, UserType userType, LanguageTestCreateDto languageTestCreateDto) {
		assertUtil.assertUserType(userType, UserType.일반회원, "어학시험 등록");
		Member member = memberRepository.findByIdAndDeletedDateIsNull(id)
			.orElseThrow(() -> new CustomEntityNotFoundException("회원"));
		
		LanguageTest languageTest = LanguageTest.of(member, languageTestCreateDto);
		
		languageTestRepository.save(languageTest);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<LanguageTestResponseDto> readLanguageTestList(Long id) {

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
			.orElseThrow(() -> new CustomEntityNotFoundException("어학시험"));
		
		assertUtil.assertId(id, languageTest, "수정");
		
		languageTest.updateLanguageTest(languageTestUpdateDto);
		
	}

	@Override
	public void deleteLanguageTest(Long id, Long languageTestId) {
		
		LanguageTest languageTest = languageTestRepository.findById(languageTestId)
			.orElseThrow(() -> new CustomEntityNotFoundException("어학시험"));
		
		assertUtil.assertId(id, languageTest, "삭제");
		
		languageTestRepository.delete(languageTest);
		
	}

}
