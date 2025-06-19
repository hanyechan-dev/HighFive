package com.jobPrize.service.member.careerDescription;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.customException.CustomEntityNotFoundException;
import com.jobPrize.dto.member.careerDescription.CareerDescriptionContentCreateDto;
import com.jobPrize.dto.member.careerDescription.CareerDescriptionContentResponseDto;
import com.jobPrize.dto.member.careerDescription.CareerDescriptionCreateDto;
import com.jobPrize.dto.member.careerDescription.CareerDescriptionResponseDto;
import com.jobPrize.dto.member.careerDescription.CareerDescriptionSummaryDto;
import com.jobPrize.dto.member.careerDescription.CareerDescriptionUpdateDto;
import com.jobPrize.entity.member.CareerDescription;
import com.jobPrize.entity.member.CareerDescriptionContent;
import com.jobPrize.entity.member.Member;
import com.jobPrize.enumerate.UserType;
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

	private final static String ENTITY_NAME = "경력기술서";

	private final static UserType ALLOWED_USER_TYPE = UserType.일반회원;
	
	@Override
	public CareerDescriptionResponseDto createCareerDescription(Long id, UserType userType, CareerDescriptionCreateDto careerDescriptionCreateDto) {
		
		String action = "등록";

		assertUtil.assertUserType(userType, ALLOWED_USER_TYPE, ENTITY_NAME, action);
		
		Member member = memberRepository.findByIdAndDeletedDateIsNull(id)
			.orElseThrow(() -> new CustomEntityNotFoundException("회원"));

		CareerDescription careerDescription = CareerDescription.of(member, careerDescriptionCreateDto);
		careerDescriptionRepository.save(careerDescription);

		List<CareerDescriptionContentCreateDto> careerDescriptionContentCreateDtos = careerDescriptionCreateDto.getContents();
		List<CareerDescriptionContentResponseDto> careerDescriptionContentResponseDtos = new ArrayList<>();
		
		for(CareerDescriptionContentCreateDto careerDescriptionContentCreateDto : careerDescriptionContentCreateDtos) {
			CareerDescriptionContentResponseDto careerDescriptionContentResponseDto = careerDescriptionContentService.createCareerDescriptionContent(careerDescription, careerDescriptionContentCreateDto);
			careerDescriptionContentResponseDtos.add(careerDescriptionContentResponseDto);
		}

		return CareerDescriptionResponseDto.of(careerDescription, careerDescriptionContentResponseDtos);
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

		String action = "조회";

		CareerDescription careerDescription = careerDescriptionRepository.findById(careerDescriptionId)
			.orElseThrow(() -> new CustomEntityNotFoundException(ENTITY_NAME));

		Long memberId = careerDescriptionRepository.findMemberIdByCareerDescriptionId(careerDescriptionId)
			.orElseThrow(() -> new CustomEntityNotFoundException("회원"));
		
		assertUtil.assertId(id, memberId, ENTITY_NAME, action);

		List<CareerDescriptionContent> careerDescriptionContents = careerDescription.getCareerDescriptionContents();
		List<CareerDescriptionContentResponseDto> careerDescriptionContentResponseDtos = new ArrayList<>();
		
		
		for(CareerDescriptionContent careerDescriptionContent : careerDescriptionContents) {
			careerDescriptionContentResponseDtos.add(CareerDescriptionContentResponseDto.from(careerDescriptionContent));
		}
		
		return CareerDescriptionResponseDto.of(careerDescription,careerDescriptionContentResponseDtos);
	}

	@Override
	public CareerDescriptionResponseDto updateCareerDescription(Long id, CareerDescriptionUpdateDto careerDescriptionUpdateDto) {

		String action = "수정";

		CareerDescription careerDescription = careerDescriptionRepository.findById(careerDescriptionUpdateDto.getId())
			.orElseThrow(() -> new CustomEntityNotFoundException(ENTITY_NAME));

		Long memberId = careerDescriptionRepository.findMemberIdByCareerDescriptionId(careerDescriptionUpdateDto.getId())
			.orElseThrow(() -> new CustomEntityNotFoundException("회원"));
		
		assertUtil.assertId(id, memberId, ENTITY_NAME, action);

		careerDescription.updateCareerDescription(careerDescriptionUpdateDto);
		
		careerDescription.getCareerDescriptionContents().clear();

		
		List<CareerDescriptionContentCreateDto> careerDescriptionContentCreateDtos = careerDescriptionUpdateDto.getContents();
		List<CareerDescriptionContentResponseDto> careerDescriptionContentResponseDtos = new ArrayList<>();
		for(CareerDescriptionContentCreateDto careerDescriptionContentCreateDto : careerDescriptionContentCreateDtos) {
			CareerDescriptionContentResponseDto careerDescriptionContentResponseDto = careerDescriptionContentService.createCareerDescriptionContent(careerDescription, careerDescriptionContentCreateDto);
			careerDescriptionContentResponseDtos.add(careerDescriptionContentResponseDto);
		}

		return CareerDescriptionResponseDto.of(careerDescription,careerDescriptionContentResponseDtos);
	}

	@Override
	public void deleteCareerDescription(Long id, Long careerDescriptionId) {

		String action = "삭제";

		CareerDescription careerDescription = careerDescriptionRepository.findById(careerDescriptionId)
			.orElseThrow(() -> new CustomEntityNotFoundException(ENTITY_NAME));

		Long memberId = careerDescriptionRepository.findMemberIdByCareerDescriptionId(careerDescriptionId)
			.orElseThrow(() -> new CustomEntityNotFoundException("회원"));
		
		assertUtil.assertId(id, memberId, ENTITY_NAME, action);

		careerDescriptionRepository.delete(careerDescription);
		
	}

}
