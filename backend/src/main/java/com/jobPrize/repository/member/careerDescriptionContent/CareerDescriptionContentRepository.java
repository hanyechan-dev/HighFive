package com.jobPrize.repository.member.careerDescriptionContent;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobPrize.entity.member.CareerDescription;
import com.jobPrize.entity.member.CareerDescriptionContent;

public interface CareerDescriptionContentRepository extends JpaRepository<CareerDescriptionContent, Long> {
	void deleteByCareerDescription(CareerDescription careerDescription);

}
