package com.jobPrize.repository.member.coverLetter;

import java.util.Optional;

import com.jobPrize.entity.member.CoverLetter;

public interface CoverLetterRepositoryCustom {
	Optional<CoverLetter> findWithCoverLetterContentsById(Long id);
}
