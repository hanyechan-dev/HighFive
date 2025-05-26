package com.jobPrize.service.member.career;

import java.util.List;

import com.jobPrize.dto.member.career.CareerCreateDto;
import com.jobPrize.dto.member.career.CareerResponseDto;
import com.jobPrize.dto.member.career.CareerUpdateDto;
import com.jobPrize.entity.common.UserType;

public interface CareerService {

	void createCareer(Long id, UserType userType, CareerCreateDto careerCreateDto);

	List<CareerResponseDto> readCareerList(Long id);

	void updateCareer(Long id, CareerUpdateDto careerUpdateDto);
	
	void deleteCareer(Long id, Long careerId);

}
