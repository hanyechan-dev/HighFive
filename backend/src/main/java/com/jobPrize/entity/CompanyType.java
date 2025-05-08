package com.jobPrize.entity;

public enum CompanyType {
	LARGE("대기업"), MEDIUM("중견 기업"), SMALL("중소 기업");

	private final String description;

	CompanyType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}