package com.jobPrize.service.member.careerDescriptionContent;

import com.jobPrize.dto.member.careerDescription.CareerDescriptionContentCreateDto;
import com.jobPrize.dto.member.careerDescription.CareerDescriptionContentUpdateDto;
import com.jobPrize.entity.member.CareerDescription;

public interface CareerDescriptionContentService {
    void createCareerDescriptionContent(CareerDescription careerDescription, CareerDescriptionContentCreateDto careerDescriptionContentCreateDto);
    void updateCareerDescriptionContent(CareerDescriptionContentUpdateDto careerDescriptionContentUpdateDto);
    void deleteCareerDescriptionContent(Long careerDescriptionContentId);
}
