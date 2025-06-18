package com.jobPrize.service.member.languageTest;

import java.util.List;

import com.jobPrize.dto.member.languageTest.LanguageTestCreateDto;
import com.jobPrize.dto.member.languageTest.LanguageTestResponseDto;
import com.jobPrize.dto.member.languageTest.LanguageTestUpdateDto;
import com.jobPrize.enumerate.UserType;

public interface LanguageTestService {
    public LanguageTestResponseDto createLanguageTest(Long id, UserType userType, LanguageTestCreateDto languageTestCreateDto);
    public List<LanguageTestResponseDto> readLanguageTestList(Long id);
    public LanguageTestResponseDto updateLanguageTest(Long id, LanguageTestUpdateDto languageTestUpdateDto);
    public void deleteLanguageTest(Long id, Long languageTestId);
}
