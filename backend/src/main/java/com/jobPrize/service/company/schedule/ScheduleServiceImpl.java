package com.jobPrize.service.company.schedule;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.customException.CustomEntityNotFoundException;
import com.jobPrize.dto.company.schedule.ScheduleCreateDto;
import com.jobPrize.dto.company.schedule.ScheduleResponseDto;
import com.jobPrize.dto.company.schedule.ScheduleSummaryDto;
import com.jobPrize.dto.company.schedule.ScheduleUpdateDto;
import com.jobPrize.entity.common.UserType;
import com.jobPrize.entity.company.Company;
import com.jobPrize.entity.company.Schedule;
import com.jobPrize.repository.company.company.CompanyRepository;
import com.jobPrize.repository.company.schedule.ScheduleRepository;
import com.jobPrize.util.AssertUtil;

import lombok.RequiredArgsConstructor;


@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService{
	
	private final ScheduleRepository scheduleRepository;
	
	private final CompanyRepository companyRepository;

	private final AssertUtil assertUtil;


	@Override
	public void createSchedule(Long id, UserType userType, ScheduleCreateDto scheduleCreateDto) {
		assertUtil.assertUserType(userType, UserType.기업회원, "일정 등록");
		
		Company company = companyRepository.findById(id)
				.orElseThrow(()-> new CustomEntityNotFoundException("기업"));
		
		Schedule schedule = Schedule.of(company, scheduleCreateDto);
		
		scheduleRepository.save(schedule);
		
	}

	@Override
	public List<ScheduleSummaryDto> readScheduleList(Long id) {
		List<Schedule> schedules = scheduleRepository.findAllByCompanyId(id);
		List<ScheduleSummaryDto> scheduleSummaryDtos = new ArrayList<>();
		for(Schedule schedule : schedules) {
			ScheduleSummaryDto scheduleSummaryDto = ScheduleSummaryDto.from(schedule);
			scheduleSummaryDtos.add(scheduleSummaryDto);
		}
		return scheduleSummaryDtos;
	}

	@Override
	public ScheduleResponseDto readSchedule(Long id, Long scheduleId) {
		Schedule schedule = scheduleRepository.findById(scheduleId)
				.orElseThrow(() -> new CustomEntityNotFoundException("일정"));
		
		assertUtil.assertId(id, schedule, "조회");
		
		return ScheduleResponseDto.from(schedule);
	}

	@Override
	public void updateSchedule(Long id, ScheduleUpdateDto scheduleUpdateDto) {
		Schedule schedule = scheduleRepository.findById(scheduleUpdateDto.getId())
				.orElseThrow(() -> new CustomEntityNotFoundException("일정"));
		
		assertUtil.assertId(id, schedule, "수정");
		
		schedule.updateSchedule(scheduleUpdateDto);
	}

	@Override
	public void deleteSchedule(Long id, Long scheduleId) {
		Schedule schedule = scheduleRepository.findById(scheduleId)
				.orElseThrow(() -> new CustomEntityNotFoundException("일정"));
		
		assertUtil.assertId(id, schedule, "삭제");
		
		scheduleRepository.delete(schedule);
	}


}
