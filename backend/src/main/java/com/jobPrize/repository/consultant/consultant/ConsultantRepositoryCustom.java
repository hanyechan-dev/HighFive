package com.jobPrize.repository.consultant.consultant;

import java.util.Optional;

import com.jobPrize.entity.consultant.Consultant;

public interface ConsultantRepositoryCustom {
	Optional<Consultant> findByIdAndDeletedDateIsNull(Long id);
}
