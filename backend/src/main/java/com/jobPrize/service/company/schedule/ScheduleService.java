package com.jobPrize.service.company.schedule;

import java.util.List;

import com.jobPrize.dto.company.schedule.ScheduleCreateDto;
import com.jobPrize.dto.company.schedule.ScheduleResponseDto;
import com.jobPrize.dto.company.schedule.ScheduleSummaryDto;
import com.jobPrize.dto.company.schedule.ScheduleUpdateDto;
import com.jobPrize.entity.common.UserType;

public interface ScheduleService {
	void createSchedule(Long id, UserType userType, ScheduleCreateDto scheduleCreateDto);
	List<ScheduleSummaryDto> readScheduleList(Long id);
	ScheduleResponseDto readSchedule(Long id, Long scheduleId);
    void updateSchedule(Long id, ScheduleUpdateDto scheduleUpdateDto);
    void deleteSchedule(Long id, Long scheduleId);
}
