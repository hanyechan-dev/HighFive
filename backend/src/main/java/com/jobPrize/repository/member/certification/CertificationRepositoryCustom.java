package com.jobPrize.repository.member.certification;

import java.util.List;
import java.util.Optional;

import com.jobPrize.entity.member.Certification;

public interface CertificationRepositoryCustom {
	List<Certification> findAllByMemberId(Long id);
	Optional<Long> findMemberIdByCertificationId(Long id);
}
