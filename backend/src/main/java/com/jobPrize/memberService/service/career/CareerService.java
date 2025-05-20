package com.jobPrize.memberService.service.career;

import java.util.List;

import com.jobPrize.memberService.dto.career.CareerCreateDto;
import com.jobPrize.memberService.dto.career.CareerResponseDto;
import com.jobPrize.memberService.dto.career.CareerUpdateDto;

public interface CareerService {
	void createCareer(Long id, CareerCreateDto careerCreateDto);
	List<CareerResponseDto> getCareerList(Long id);
	void updateCareer(Long id, CareerUpdateDto careerUpdateDto);
	void deleteCareer(Long id, Long careerId);

}
