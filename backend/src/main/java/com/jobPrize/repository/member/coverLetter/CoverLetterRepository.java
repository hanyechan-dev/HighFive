package com.jobPrize.repository.member.coverLetter;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobPrize.entity.member.CoverLetter;

public interface CoverLetterRepository extends JpaRepository<CoverLetter, Long>,CoverLetterRepositoryCustom {

}
