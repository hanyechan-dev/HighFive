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
import com.jobPrize.entity.company.Company;
import com.jobPrize.entity.company.Schedule;
import com.jobPrize.enumerate.ApprovalStatus;
import com.jobPrize.enumerate.UserType;
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

	private static final String ENTITY_NAME = "일정";


	@Override
	public void createSchedule(Long id, UserType userType, ApprovalStatus approvalStatus, boolean isSubscribed, ScheduleCreateDto scheduleCreateDto) {
		
		String action = "등록";
		
		assertUtil.assertForCompany(userType, approvalStatus, isSubscribed, ENTITY_NAME, action);
		
		Company company = companyRepository.findByIdAndDeletedDateIsNull(id)
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

		String action = "조회";
		
		Schedule schedule = scheduleRepository.findById(scheduleId)
				.orElseThrow(() -> new CustomEntityNotFoundException(ENTITY_NAME));

		Long ownerId = scheduleRepository.findCompanyIdByScheduleId(scheduleId)
				.orElseThrow(() -> new CustomEntityNotFoundException("소유자"));
		
		assertUtil.assertId(id, ownerId, ENTITY_NAME, action);
		
		return ScheduleResponseDto.from(schedule);
	}

	@Override
	public void updateSchedule(Long id, UserType userType, ApprovalStatus approvalStatus, boolean isSubscribed, ScheduleUpdateDto scheduleUpdateDto) {
		
		String action = "수정";
		
		assertUtil.assertForCompany(userType, approvalStatus, isSubscribed, ENTITY_NAME, action);
		
		Schedule schedule = scheduleRepository.findById(scheduleUpdateDto.getId())
				.orElseThrow(() -> new CustomEntityNotFoundException(ENTITY_NAME));

		Long ownerId = scheduleRepository.findCompanyIdByScheduleId(scheduleUpdateDto.getId())
				.orElseThrow(() -> new CustomEntityNotFoundException("소유자"));
		
		assertUtil.assertId(id, ownerId, ENTITY_NAME, action);
		
		schedule.updateSchedule(scheduleUpdateDto);
	}

	@Override
	public void deleteSchedule(Long id, Long scheduleId) {

		String action = "삭제";
		
		Schedule schedule = scheduleRepository.findById(scheduleId)
				.orElseThrow(() -> new CustomEntityNotFoundException(ENTITY_NAME));

		Long ownerId = scheduleRepository.findCompanyIdByScheduleId(scheduleId)
				.orElseThrow(() -> new CustomEntityNotFoundException("소유자"));
		
		assertUtil.assertId(id, ownerId, ENTITY_NAME, action);
		
		scheduleRepository.delete(schedule);
	}


}
