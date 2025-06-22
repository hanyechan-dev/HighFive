package com.jobPrize.dto.admin.service;

import java.time.LocalDate;

import lombok.Getter;

@Getter
public class PaymentCountDto {
	private LocalDate date;
	private Long payAmount;
	
	public PaymentCountDto(java.sql.Date date, Long payAmount) {
		this.date = date.toLocalDate();
		this.payAmount = payAmount;
	}
}

