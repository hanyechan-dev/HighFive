package com.jobPrize.memberService.service.careerDescriptionContent;

import com.jobPrize.entity.member.CareerDescription;
import com.jobPrize.memberService.dto.careerDescription.CareerDescriptionContentCreateDto;
import com.jobPrize.memberService.dto.careerDescription.CareerDescriptionContentUpdateDto;

public interface CareerDescriptionContentService {
    void createCareerDescriptionContent(CareerDescription careerDescription, CareerDescriptionContentCreateDto careerDescriptionContentCreateDto);
    void updateCareerDescriptionContent(CareerDescriptionContentUpdateDto careerDescriptionContentUpdateDto);
    void deleteCareerDescriptionContent(Long careerDescriptionContentId);
}
