package com.jobPrize.memberService.service.careerDescriptionContent;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.entity.member.CareerDescription;
import com.jobPrize.entity.member.CareerDescriptionContent;
import com.jobPrize.memberService.dto.careerDescription.CareerDescriptionContentCreateDto;
import com.jobPrize.memberService.dto.careerDescription.CareerDescriptionContentUpdateDto;
import com.jobPrize.repository.member.careerDescriptionContent.CareerDescriptionContentRepository;

import jakarta.persistence.EntityNotFoundException;
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
			.orElseThrow(() -> new EntityNotFoundException("존재하지 않는 경력기술서 내용입니다."));
		careerDescriptionContent.updateContent(careerDescriptionContentUpdateDto);
		
	}

	@Override
	public void deleteCareerDescriptionContent(Long careerDescriptionContentId) {
		CareerDescriptionContent careerDescriptionContent = careerDescriptionContentRepository.findById(careerDescriptionContentId)
			.orElseThrow(() -> new EntityNotFoundException("존재하지 않는 경력기술서 내용입니다."));
		careerDescriptionContentRepository.delete(careerDescriptionContent);
		
	}

}
