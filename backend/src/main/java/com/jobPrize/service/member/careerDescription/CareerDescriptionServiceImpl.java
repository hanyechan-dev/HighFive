package com.jobPrize.service.member.careerDescription;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.customException.CustomEntityNotFoundException;
import com.jobPrize.dto.member.careerDescription.CareerDescriptionContentCreateDto;
import com.jobPrize.dto.member.careerDescription.CareerDescriptionContentResponseDto;
import com.jobPrize.dto.member.careerDescription.CareerDescriptionContentUpdateDto;
import com.jobPrize.dto.member.careerDescription.CareerDescriptionCreateDto;
import com.jobPrize.dto.member.careerDescription.CareerDescriptionResponseDto;
import com.jobPrize.dto.member.careerDescription.CareerDescriptionSummaryDto;
import com.jobPrize.dto.member.careerDescription.CareerDescriptionUpdateDto;
import com.jobPrize.entity.common.UserType;
import com.jobPrize.entity.member.CareerDescription;
import com.jobPrize.entity.member.CareerDescriptionContent;
import com.jobPrize.entity.member.Member;
import com.jobPrize.repository.member.careerDescription.CareerDescriptionRepository;
import com.jobPrize.repository.member.member.MemberRepository;
import com.jobPrize.service.member.careerDescriptionContent.CareerDescriptionContentService;
import com.jobPrize.util.AssertUtil;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CareerDescriptionServiceImpl implements CareerDescriptionService {
	
	private final CareerDescriptionRepository careerDescriptionRepository;

	private final MemberRepository memberRepository;

	private final CareerDescriptionContentService careerDescriptionContentService;

	private final AssertUtil assertUtil;
	
	@Override
	public void createCareerDescription(Long id, UserType userType, CareerDescriptionCreateDto careerDescriptionCreateDto) {
		
		assertUtil.assertUserType(userType, UserType.일반회원, "경력기술서 등록");
		
		Member member = memberRepository.findById(id)
			.orElseThrow(() -> new CustomEntityNotFoundException("회원"));

		CareerDescription careerDescription = CareerDescription.of(member, careerDescriptionCreateDto);
		careerDescriptionRepository.save(careerDescription);

		List<CareerDescriptionContentCreateDto> careerDescriptionContentCreateDtos = careerDescriptionCreateDto.getContents();
		
		for(CareerDescriptionContentCreateDto careerDescriptionContentCreateDto : careerDescriptionContentCreateDtos) {
			careerDescriptionContentService.createCareerDescriptionContent(careerDescription, careerDescriptionContentCreateDto);
		}
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<CareerDescriptionSummaryDto> readCareerDescriptionList(Long id) {

		List<CareerDescription> careerDescriptions = careerDescriptionRepository.findAllByMemberId(id);
		List<CareerDescriptionSummaryDto> careerDescriptionSummaryDtos = new ArrayList<>();
		for(CareerDescription careerDescription : careerDescriptions) {
			careerDescriptionSummaryDtos.add(CareerDescriptionSummaryDto.from(careerDescription));
		}
		return careerDescriptionSummaryDtos;
	}

	@Override
	@Transactional(readOnly = true)
	public CareerDescriptionResponseDto readCareerDescription(Long id, Long careerDescriptionId) {
		CareerDescription careerDescription = careerDescriptionRepository.findById(careerDescriptionId)
			.orElseThrow(() -> new CustomEntityNotFoundException("경력기술서"));
		
		
		assertUtil.assertId(id, careerDescription, "조회");

		List<CareerDescriptionContent> careerDescriptionContents = careerDescription.getCareerDescriptionContents();
		List<CareerDescriptionContentResponseDto> careerDescriptionContentResponseDtos = new ArrayList<>();
		
		
		for(CareerDescriptionContent careerDescriptionContent : careerDescriptionContents) {
			careerDescriptionContentResponseDtos.add(CareerDescriptionContentResponseDto.from(careerDescriptionContent));
		}
		
		return CareerDescriptionResponseDto.of(careerDescription,careerDescriptionContentResponseDtos);
	}

	@Override
	public void updateCareerDescription(Long id, CareerDescriptionUpdateDto careerDescriptionUpdateDto) {
		CareerDescription careerDescription = careerDescriptionRepository.findById(careerDescriptionUpdateDto.getId())
			.orElseThrow(() -> new CustomEntityNotFoundException("경력기술서"));

		assertUtil.assertId(id, careerDescription, "수정");

		careerDescription.updateCareerDescription(careerDescriptionUpdateDto);
		List<CareerDescriptionContentUpdateDto> careerDescriptionContentUpdateDtos = careerDescriptionUpdateDto.getContents();
		for(CareerDescriptionContentUpdateDto careerDescriptionContentUpdateDto : careerDescriptionContentUpdateDtos) {
			careerDescriptionContentService.updateCareerDescriptionContent(careerDescriptionContentUpdateDto);
		}
		
	}

	@Override
	public void deleteCareerDescription(Long id, Long careerDescriptionId) {
		CareerDescription careerDescription = careerDescriptionRepository.findById(careerDescriptionId)
			.orElseThrow(() -> new CustomEntityNotFoundException("경력기술서"));

		assertUtil.assertId(id, careerDescription, "삭제");

		List<CareerDescriptionContent> careerDescriptionContents = careerDescription.getCareerDescriptionContents();
		for(CareerDescriptionContent careerDescriptionContent : careerDescriptionContents) {
			careerDescriptionContentService.deleteCareerDescriptionContent(careerDescriptionContent.getId());
		}


		careerDescriptionRepository.delete(careerDescription);
		
	}

}
