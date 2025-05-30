package com.jobPrize.service.company.schedule;

import java.util.List;

import com.jobPrize.dto.company.schedule.ScheduleCreateDto;
import com.jobPrize.dto.company.schedule.ScheduleResponseDto;
import com.jobPrize.dto.company.schedule.ScheduleSummaryDto;
import com.jobPrize.dto.company.schedule.ScheduleUpdateDto;
import com.jobPrize.enumerate.ApprovalStatus;
import com.jobPrize.enumerate.UserType;

public interface ScheduleService {
	void createSchedule(Long id, UserType userType, ApprovalStatus approvalStatus, boolean isSubscribed, ScheduleCreateDto scheduleCreateDto);
	List<ScheduleSummaryDto> readScheduleList(Long id);
	ScheduleResponseDto readSchedule(Long id, Long scheduleId);
    void updateSchedule(Long id, UserType userType, ApprovalStatus approvalStatus, boolean isSubscribed, ScheduleUpdateDto scheduleUpdateDto);
    void deleteSchedule(Long id, Long scheduleId);
}
