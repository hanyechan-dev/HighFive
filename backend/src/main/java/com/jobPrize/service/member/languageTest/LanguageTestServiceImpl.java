package com.jobPrize.service.member.languageTest;

import java.time.LocalDateTime;
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
import com.jobPrize.repository.memToCom.similarity.SimilarityRepository;
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
	
	private final SimilarityRepository similarityRepository;

	private final AssertUtil assertUtil;

	private final static String ENTITY_NAME = "어학시험";

	private final static UserType ALLOWED_USER_TYPE = UserType.일반회원;

	@Override
	public LanguageTestResponseDto createLanguageTest(Long id, UserType userType, LanguageTestCreateDto languageTestCreateDto) {

		String action = "등록";

		assertUtil.assertUserType(userType, ALLOWED_USER_TYPE, ENTITY_NAME, action);
		Member member = memberRepository.findByIdAndDeletedDateIsNull(id)
			.orElseThrow(() -> new CustomEntityNotFoundException("회원"));
		
		LanguageTest languageTest = LanguageTest.of(member, languageTestCreateDto);
		
		languageTestRepository.save(languageTest);
		
		member.updateTime(LocalDateTime.now());
		
		similarityRepository.deleteByMember(member);
		
		return LanguageTestResponseDto.from(languageTest);
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
	public LanguageTestResponseDto updateLanguageTest(Long id, LanguageTestUpdateDto languageTestUpdateDto) {

		String action = "수정";

		Long languageTestId = languageTestUpdateDto.getId();

		LanguageTest languageTest = languageTestRepository.findById(languageTestId)
			.orElseThrow(() -> new CustomEntityNotFoundException(ENTITY_NAME));

		Long ownerId = languageTestRepository.findMemberIdByLanguageTestId(languageTestId)
			.orElseThrow(() -> new CustomEntityNotFoundException("소유자"));

		assertUtil.assertId(id, ownerId, ENTITY_NAME, action);
		
		languageTest.updateLanguageTest(languageTestUpdateDto);
		
		Member member = languageTest.getMember();
		
		member.updateTime(LocalDateTime.now());
		
		similarityRepository.deleteByMember(member);
		
		return LanguageTestResponseDto.from(languageTest);
	}

	@Override
	public void deleteLanguageTest(Long id, Long languageTestId) {

		String action = "삭제";
		
		LanguageTest languageTest = languageTestRepository.findById(languageTestId)
			.orElseThrow(() -> new CustomEntityNotFoundException(ENTITY_NAME));

		Long ownerId = languageTestRepository.findMemberIdByLanguageTestId(languageTestId)
			.orElseThrow(() -> new CustomEntityNotFoundException("소유자"));

		assertUtil.assertId(id, ownerId, ENTITY_NAME, action);
		
		Member member = languageTest.getMember();
		
		languageTestRepository.delete(languageTest);
		
		languageTestRepository.flush();
		
		member.updateTime(LocalDateTime.now());
		
		similarityRepository.deleteByMember(member);
		
	}

}
