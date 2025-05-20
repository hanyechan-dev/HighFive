package com.jobPrize.memberService.service.languageTest;

import java.util.List;

import com.jobPrize.memberService.dto.languageTest.LanguageTestCreateDto;
import com.jobPrize.memberService.dto.languageTest.LanguageTestResponseDto;
import com.jobPrize.memberService.dto.languageTest.LanguageTestUpdateDto;

public interface LanguageTestService {
    public void createLanguageTest(Long id, LanguageTestCreateDto languageTestCreateDto);
    public List<LanguageTestResponseDto> getLanguageTestList(Long id);
    public void updateLanguageTest(Long id, LanguageTestUpdateDto languageTestUpdateDto);
    public void deleteLanguageTest(Long id, Long languageTestId);
}
