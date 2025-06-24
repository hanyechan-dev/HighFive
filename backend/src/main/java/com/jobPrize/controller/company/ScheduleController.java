package com.jobPrize.controller.company;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobPrize.dto.common.id.IdDto;
import com.jobPrize.dto.company.schedule.ScheduleCreateDto;
import com.jobPrize.dto.company.schedule.ScheduleResponseDto;
import com.jobPrize.dto.company.schedule.ScheduleSummaryDto;
import com.jobPrize.dto.company.schedule.ScheduleUpdateDto;
import com.jobPrize.enumerate.ApprovalStatus;
import com.jobPrize.enumerate.UserType;
import com.jobPrize.service.company.schedule.ScheduleService;
import com.jobPrize.util.SecurityUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("schedules")
@RequiredArgsConstructor
public class ScheduleController {

	private final ScheduleService scheduleService;

	@GetMapping
	public ResponseEntity<List<ScheduleSummaryDto>> readMySchedules() {

		Long id = SecurityUtil.getId();

		List<ScheduleSummaryDto> scheduleSummaryDtos = scheduleService.readScheduleList(id);

		return ResponseEntity.status(HttpStatus.OK).body(scheduleSummaryDtos);
	}

	@PostMapping("/detail")
	public ResponseEntity<ScheduleResponseDto> readMyJobPosting(@RequestBody @Valid IdDto IdDto) {

		Long id = SecurityUtil.getId();

		ScheduleResponseDto scheduleResponseDto = scheduleService.readSchedule(id, IdDto.getId());

		return ResponseEntity.status(HttpStatus.OK).body(scheduleResponseDto);
	}

	@PostMapping
	public ResponseEntity<Void> createMySchedule(@RequestBody @Valid ScheduleCreateDto scheduleCreateDto) {

		Long id = SecurityUtil.getId();

		UserType userType = SecurityUtil.getUserType();
		
		ApprovalStatus approvalStatus = SecurityUtil.getApprovalStatus();

		boolean isSubscribed = SecurityUtil.isSubscribed();

		scheduleService.createSchedule(id, userType, approvalStatus, isSubscribed, scheduleCreateDto);

		return ResponseEntity.status(HttpStatus.CREATED).build();

	}

	@PutMapping
	public ResponseEntity<Void> updateMySchedule(@RequestBody @Valid ScheduleUpdateDto scheduleUpdateDto) {

		Long id = SecurityUtil.getId();

		UserType userType = SecurityUtil.getUserType();
		
		ApprovalStatus approvalStatus = SecurityUtil.getApprovalStatus();

		boolean isSubscribed = SecurityUtil.isSubscribed();

		scheduleService.updateSchedule(id, userType, approvalStatus, isSubscribed, scheduleUpdateDto);

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@PostMapping("/deletion")
	public ResponseEntity<Void> deletMySchedule(@RequestBody @Valid IdDto IdDto) {

		Long id = SecurityUtil.getId();

		scheduleService.deleteSchedule(id, IdDto.getId());

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
