package com.jobPrize.service.member.careerDescriptionContent;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.customException.CustomEntityNotFoundException;
import com.jobPrize.dto.member.careerDescription.CareerDescriptionContentCreateDto;
import com.jobPrize.dto.member.careerDescription.CareerDescriptionContentUpdateDto;
import com.jobPrize.entity.member.CareerDescription;
import com.jobPrize.entity.member.CareerDescriptionContent;
import com.jobPrize.repository.member.careerDescriptionContent.CareerDescriptionContentRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CareerDescriptionContentServiceImpl implements CareerDescriptionContentService {
	
    private final CareerDescriptionContentRepository careerDescriptionContentRepository;

	@Override
	public void createCareerDescriptionContent(CareerDescription careerDescription, CareerDescriptionContentCreateDto careerDescriptionContentCreateDto) {
		CareerDescriptionContent careerDescriptionContent = CareerDescriptionContent.of(careerDescription, careerDescriptionContentCreateDto);
		careerDescriptionContentRepository.save(careerDescriptionContent);
		
	}

	@Override
	public void updateCareerDescriptionContent(CareerDescriptionContentUpdateDto careerDescriptionContentUpdateDto) {
		CareerDescriptionContent careerDescriptionContent = careerDescriptionContentRepository.findById(careerDescriptionContentUpdateDto.getId())
				.orElseThrow(() -> new CustomEntityNotFoundException("경력기술서"));
		careerDescriptionContent.updateContent(careerDescriptionContentUpdateDto);
		
	}

	@Override
	public void deleteCareerDescriptionContent(Long careerDescriptionContentId) {
		CareerDescriptionContent careerDescriptionContent = careerDescriptionContentRepository.findById(careerDescriptionContentId)
				.orElseThrow(() -> new CustomEntityNotFoundException("자기소개서"));
		careerDescriptionContentRepository.delete(careerDescriptionContent);
		
	}

}
