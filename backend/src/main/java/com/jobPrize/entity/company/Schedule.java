package com.jobPrize.entity.company;

import java.time.LocalDate;

import com.jobPrize.dto.company.schedule.ScheduleCreateDto;
import com.jobPrize.dto.company.schedule.ScheduleUpdateDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@Entity
@Table(name = "schedule")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Schedule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "schedule_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private Company company;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "content")
	private String content;
	
	@Column(name = "date")
	private LocalDate date;

	public void updateSchedule(ScheduleUpdateDto scheduleUpdateDto) {
		this.title = scheduleUpdateDto.getTitle();
		this.date = scheduleUpdateDto.getDate();
		this.content = scheduleUpdateDto.getContent();
	}

	public static Schedule of(Company company,ScheduleCreateDto scheduleCreateDto) {
		return Schedule.builder()
			.company(company)
			.title(scheduleCreateDto.getTitle())
			.date(scheduleCreateDto.getDate())
			.content(scheduleCreateDto.getContent())
			.build();
	}
	
	
}