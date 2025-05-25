package com.jobPrize.service.member.career;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.customException.CustomEntityNotFoundException;
import com.jobPrize.dto.member.career.CareerCreateDto;
import com.jobPrize.dto.member.career.CareerResponseDto;
import com.jobPrize.dto.member.career.CareerUpdateDto;
import com.jobPrize.entity.common.UserType;
import com.jobPrize.entity.member.Career;
import com.jobPrize.entity.member.Member;
import com.jobPrize.repository.member.career.CareerRepository;
import com.jobPrize.repository.member.member.MemberRepository;
import com.jobPrize.util.AssertUtil;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CareerServiceImpl implements CareerService {
	
	private final CareerRepository careerRepository;

	private final MemberRepository memberRepository;

	private final AssertUtil assertUtil;

	@Override
	public void createCareer(Long id, UserType userType, CareerCreateDto careerCreateDto) {
		
		if(careerCreateDto.getStartDate().isAfter(careerCreateDto.getEndDate())) {
			throw new IllegalArgumentException("퇴사일은 입사일보다 빠를 수 없습니다.");
		}


		Member member = memberRepository.findById(id)
				.orElseThrow(() -> new CustomEntityNotFoundException("회원"));
		

		
		Career career = Career.of(member, careerCreateDto);
		
		careerRepository.save(career);
		
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<CareerResponseDto> readCareerList(Long id) {

		List<Career> careers = careerRepository.findAllByMemberId(id);
		List<CareerResponseDto> careerResponseDtos = new ArrayList<>();
	
		for (Career career : careers) {
			careerResponseDtos.add(CareerResponseDto.from(career));
		}
		return careerResponseDtos;
	}

	@Override
	public void updateCareer(Long id, CareerUpdateDto careerUpdateDto) {
		
		if(careerUpdateDto.getStartDate().isAfter(careerUpdateDto.getEndDate())) {
			throw new IllegalArgumentException("퇴사일은 입사일보다 빠를 수 없습니다.");
		}
		
		Long careerId = careerUpdateDto.getId();
		Career career =	careerRepository.findById(careerId)
				.orElseThrow(() -> new CustomEntityNotFoundException("경력"));
		
		
		assertUtil.assertId(id, career, "수정");
		

		
		career.updateCareer(careerUpdateDto);
		
		
	}

	@Override
	public void deleteCareer(Long id, Long careerId) {
		Career career =	careerRepository.findById(careerId)
				.orElseThrow(() -> new CustomEntityNotFoundException("경력"));

		
		assertUtil.assertId(id, career, "삭제");
		
		careerRepository.delete(career);
	}
		
}
