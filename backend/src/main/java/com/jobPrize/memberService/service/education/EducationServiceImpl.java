package com.jobPrize.memberService.service.education;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.entity.member.Education;
import com.jobPrize.entity.member.Member;
import com.jobPrize.memberService.dto.education.EducationCreateDto;
import com.jobPrize.memberService.dto.education.EducationResponseDto;
import com.jobPrize.memberService.dto.education.EducationUpdateDto;
import com.jobPrize.repository.member.education.EducationRepository;
import com.jobPrize.repository.member.member.MemberRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class EducationServiceImpl implements EducationService {

	private final EducationRepository educationRepository;
	
	private final MemberRepository memberRepository;
	
	@Override
	public void createEducation(Long id, EducationCreateDto educationCreateDto) {
		Member member = memberRepository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException("존재하지 않는 회원입니다."));

        if(!educationCreateDto.getEnterDate().isBefore(educationCreateDto.getGraduateDate())) {
            throw new IllegalArgumentException("졸업일은 입학일보다 빠를 수 없습니다.");
        }

        Education education = Education.of(member,educationCreateDto);

        educationRepository.save(education);
		
	}

	@Override
	public List<EducationResponseDto> getEducationList(Long id) {

		List<Education> educations = educationRepository.findAllByMemberId(id);
		List<EducationResponseDto> educationResponseDtos = new ArrayList<>();

		for (Education education : educations) {
			educationResponseDtos.add(EducationResponseDto.from(education));
		}
		return educationResponseDtos;
	}

	@Override
	public void updateEducation(Long id, EducationUpdateDto educationUpdateDto) {
		Long educationId = educationUpdateDto.getId();
		Education education = educationRepository.findById(educationId)
			.orElseThrow(() -> new EntityNotFoundException("존재하지 않는 교육입니다."));


		if (!education.getMember().getId().equals(id)) {
			throw new AccessDeniedException("교육의 대상과 회원이 일치하지 않습니다.");
		}

		if (!educationUpdateDto.getEnterDate().isBefore(educationUpdateDto.getGraduateDate())) {
			throw new IllegalArgumentException("졸업일은 입학일보다 빠를 수 없습니다.");
		}

		education.updateEducation(educationUpdateDto);
		
	}

	@Override
	public void deleteEducation(Long id, Long educationId) {
        Education education = educationRepository.findById(educationId)
			.orElseThrow(() -> new EntityNotFoundException("존재하지 않는 교육입니다."));

        if (!education.getMember().getId().equals(id)) {
            throw new AccessDeniedException("교육의 대상과 회원이 일치하지 않습니다.");
        }

        educationRepository.delete(education);
		
	}
	
	

}
