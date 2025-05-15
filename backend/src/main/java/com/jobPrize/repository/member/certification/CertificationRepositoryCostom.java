package com.jobPrize.repository.member.certification;

import java.util.List;

import com.jobPrize.entity.member.Certification;

public interface CertificationRepositoryCostom {
	List<Certification> findAllByMemberId(Long id);
}
