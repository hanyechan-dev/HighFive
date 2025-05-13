package com.jobPrize.repository.company.schedule;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobPrize.entity.company.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule,Long>,ScheduleRepositoryCustom {

}
