package com.jobPrize.repository.company.schedule;

import java.util.List;
import java.util.Optional;

import com.jobPrize.entity.company.Schedule;

public interface ScheduleRepositoryCustom {
	List<Schedule> findAllByCompanyId(Long id);
	Optional<Schedule> findByScheduleId(Long id);
}
