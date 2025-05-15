package com.jobPrize.repository.member.coverLetter;

import java.util.List;
import java.util.Optional;

import com.jobPrize.entity.member.CoverLetter;

public interface CoverLetterRepositoryCustom {
	Optional<CoverLetter> findWithCoverLetterContentsByCoverLetterId(Long id);
	List<CoverLetter> findAllByMemberId(Long id);
}
