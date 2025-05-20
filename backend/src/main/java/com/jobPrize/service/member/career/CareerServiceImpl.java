package com.jobPrize.service.member.career;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.entity.member.Career;
import com.jobPrize.entity.member.Member;
import com.jobPrize.memberService.dto.career.CareerCreateDto;
import com.jobPrize.memberService.dto.career.CareerResponseDto;
import com.jobPrize.memberService.dto.career.CareerUpdateDto;
import com.jobPrize.repository.member.career.CareerRepository;
import com.jobPrize.repository.member.member.MemberRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CareerServiceImpl implements CareerService {
	
	private final CareerRepository careerRepository;

	private final MemberRepository memberRepository;

	@Override
	public void createCareer(Long id, CareerCreateDto careerCreateDto) {


		Member member = memberRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("존재하지 않는 회원입니다."));
		
		if(!careerCreateDto.getStartDate().isBefore(careerCreateDto.getEndDate())) {
			throw new IllegalArgumentException("퇴사일은 입사일보다 빠를 수 없습니다.");
		}
		
		Career career = Career.of(member, careerCreateDto);
		
		careerRepository.save(career);
		
		
	}

	@Override
	public List<CareerResponseDto> getCareerList(Long id) {

		List<Career> careers = careerRepository.findAllByMemberId(id);
		List<CareerResponseDto> careerResponseDtos = new ArrayList<>();
	
		for (Career career : careers) {
			careerResponseDtos.add(CareerResponseDto.from(career));
		}
		return careerResponseDtos;
	}

	@Override
	public void updateCareer(Long id, CareerUpdateDto careerUpdateDto) {
		Long careerId = careerUpdateDto.getId();
		Career career =	careerRepository.findById(careerId)
				.orElseThrow(() -> new EntityNotFoundException("존재하지 않는 경력입니다."));
		
		
		if(!career.getMember().getId().equals(id)) {
			throw new AccessDeniedException("경력의 대상과 회원이 일치하지 않습니다.");
		}
		
		if(!careerUpdateDto.getStartDate().isBefore(careerUpdateDto.getEndDate())) {
			throw new IllegalArgumentException("퇴사일은 입사일보다 빠를 수 없습니다.");
		}
		
		career.updateCareer(careerUpdateDto);
		
		
	}

	@Override
	public void deleteCareer(Long id, Long careerId) {
		Career career =	careerRepository.findById(careerId)
				.orElseThrow(() -> new EntityNotFoundException("존재하지 않는 경력입니다."));

		
		if(!career.getMember().getId().equals(id)) {
			throw new AccessDeniedException("경력의 대상과 회원이 일치하지 않습니다.");
		}
		
		careerRepository.delete(career);
		
		
		
	}

}
