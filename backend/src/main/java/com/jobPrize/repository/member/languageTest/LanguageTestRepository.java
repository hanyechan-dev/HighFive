package com.jobPrize.repository.member.languageTest;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobPrize.entity.member.LanguageTest;

public interface LanguageTestRepository extends JpaRepository<LanguageTest, Long>, LanguageTestRepositoryCostom {

}
