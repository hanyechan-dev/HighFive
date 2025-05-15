package com.jobPrize.repository.member.languageTest;

import java.util.List;

import com.jobPrize.entity.member.LanguageTest;

public interface LanguageTestRepositoryCostom {
	List<LanguageTest> findAllByMemberId(Long id);
}
