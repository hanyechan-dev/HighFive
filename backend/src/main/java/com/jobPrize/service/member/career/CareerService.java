package com.jobPrize.service.member.career;

import java.util.List;

import com.jobPrize.dto.member.career.CareerCreateDto;
import com.jobPrize.dto.member.career.CareerResponseDto;
import com.jobPrize.dto.member.career.CareerUpdateDto;

public interface CareerService {
	void createCareer(Long id, CareerCreateDto careerCreateDto);
	List<CareerResponseDto> readCareerList(Long id);
	void updateCareer(Long id, CareerUpdateDto careerUpdateDto);
	void deleteCareer(Long id, Long careerId);

}
