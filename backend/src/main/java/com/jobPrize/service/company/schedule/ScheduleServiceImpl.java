package com.jobPrize.service.company.schedule;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.dto.company.schedule.ScheduleCreateDto;
import com.jobPrize.dto.company.schedule.ScheduleResponseDto;
import com.jobPrize.dto.company.schedule.ScheduleSummaryDto;
import com.jobPrize.dto.company.schedule.ScheduleUpdateDto;
import com.jobPrize.entity.common.UserType;
import com.jobPrize.entity.company.Company;
import com.jobPrize.entity.company.Schedule;
import com.jobPrize.repository.company.company.CompanyRepository;
import com.jobPrize.repository.company.schedule.ScheduleRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;


@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService{
	
	private final ScheduleRepository scheduleRepository;
	
	private final CompanyRepository companyRepository;


	@Override
	public void createSchedule(Long id, UserType userType, ScheduleCreateDto scheduleCreateDto) {
		if(userType!=UserType.기업회원) {
			throw new AccessDeniedException("기업회원만 일정을 추가할 수 있습니다.");
		}
		Company company = companyRepository.findById(id)
				.orElseThrow(()-> new EntityNotFoundException("존재하지 않는 기업입니다."));
		
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
				.orElseThrow(() -> new EntityNotFoundException("존재하지 않는 일정입니다."));
		
		if(!schedule.getCompany().getId().equals(id)){
			throw new AccessDeniedException("해당 일정 조회 권한이 없습니다.");
		}
		
		return ScheduleResponseDto.from(schedule);
	}

	@Override
	public void updateSchedule(Long id, ScheduleUpdateDto scheduleUpdateDto) {
		Schedule schedule = scheduleRepository.findById(scheduleUpdateDto.getId())
				.orElseThrow(() -> new EntityNotFoundException("존재하지 않는 일정입니다."));
		
		if(!schedule.getCompany().getId().equals(id)){
			throw new AccessDeniedException("해당 일정 수정 권한이 없습니다.");
		}
		
		schedule.updateSchedule(scheduleUpdateDto);
	}

	@Override
	public void deleteSchedule(Long id, Long scheduleId) {
		Schedule schedule = scheduleRepository.findById(scheduleId)
				.orElseThrow(() -> new EntityNotFoundException("존재하지 않는 일정입니다."));
		
		if(!schedule.getCompany().getId().equals(id)){
			throw new AccessDeniedException("해당 일정 삭제 권한이 없습니다.");
		}
		
		scheduleRepository.deleteById(scheduleId);
	}


}
