package com.jobPrize.service.member.careerDescription;

import java.util.List;

import com.jobPrize.memberService.dto.careerDescription.CareerDescriptionCreateDto;
import com.jobPrize.memberService.dto.careerDescription.CareerDescriptionResponseDto;
import com.jobPrize.memberService.dto.careerDescription.CareerDescriptionSummaryDto;
import com.jobPrize.memberService.dto.careerDescription.CareerDescriptionUpdateDto;

public interface CareerDescriptionService {
    void createCareerDescription(Long id, CareerDescriptionCreateDto careerDescriptionCreateDto);
    List<CareerDescriptionSummaryDto> getCareerDescriptionList(Long id);
    CareerDescriptionResponseDto getCareerDescription(Long id, Long careerDescriptionId);
    void updateCareerDescription(Long id, CareerDescriptionUpdateDto careerDescriptionUpdateDto);
    void deleteCareerDescription(Long id, Long careerDescriptionId);

}
