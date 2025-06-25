package com.jobPrize.service.member.education;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.customException.CustomEntityNotFoundException;
import com.jobPrize.customException.CustomIllegalArgumentException;
import com.jobPrize.dto.member.education.EducationCreateDto;
import com.jobPrize.dto.member.education.EducationResponseDto;
import com.jobPrize.dto.member.education.EducationUpdateDto;
import com.jobPrize.entity.member.Education;
import com.jobPrize.entity.member.Member;
import com.jobPrize.enumerate.UserType;
import com.jobPrize.repository.member.education.EducationRepository;
import com.jobPrize.repository.member.member.MemberRepository;
import com.jobPrize.util.AssertUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class EducationServiceImpl implements EducationService {

	private final EducationRepository educationRepository;
	
	private final MemberRepository memberRepository;

	private final AssertUtil assertUtil;

	private final static String ENTITY_NAME = "학력";

	private final static UserType ALLOWED_USER_TYPE = UserType.일반회원;
	
	@Override
	public EducationResponseDto createEducation(Long id, UserType userType, EducationCreateDto educationCreateDto) {

		String action = "등록";

		assertUtil.assertUserType(userType, ALLOWED_USER_TYPE, ENTITY_NAME, action);

		Member member = memberRepository.findByIdAndDeletedDateIsNull(id)
			.orElseThrow(() -> new CustomEntityNotFoundException("회원"));

        if(educationCreateDto.getEnterDate().isAfter(educationCreateDto.getGraduateDate())) {
            throw new CustomIllegalArgumentException("졸업일은 입학일보다 빠를 수 없습니다.");
        }

        Education education = Education.of(member,educationCreateDto);

        educationRepository.save(education);
        
        return EducationResponseDto.from(education);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<EducationResponseDto> readEducationList(Long id) {

		List<Education> educations = educationRepository.findAllByMemberId(id);
		List<EducationResponseDto> educationResponseDtos = new ArrayList<>();

		for (Education education : educations) {
			educationResponseDtos.add(EducationResponseDto.from(education));
		}
		return educationResponseDtos;
	}

	@Override
	public EducationResponseDto updateEducation(Long id, EducationUpdateDto educationUpdateDto) {

		String action = "수정";

		Long educationId = educationUpdateDto.getId();

		Education education = educationRepository.findById(educationId)
			.orElseThrow(() -> new CustomEntityNotFoundException(ENTITY_NAME));

		Long ownerId = educationRepository.findMemberIdByEducationId(educationId)
			.orElseThrow(() -> new CustomEntityNotFoundException("소유자"));

		assertUtil.assertId(id, ownerId, ENTITY_NAME, action);

		if (educationUpdateDto.getGraduateDate() != null && educationUpdateDto.getEnterDate() != null && educationUpdateDto.getEnterDate().isAfter(educationUpdateDto.getGraduateDate())) {
			throw new CustomIllegalArgumentException("졸업일은 입학일보다 빠를 수 없습니다.");
		}

		education.updateEducation(educationUpdateDto);
		
		return EducationResponseDto.from(education);
	}

	@Override
	public void deleteEducation(Long id, Long educationId) {

		String action = "삭제";

        Education education = educationRepository.findById(educationId)
			.orElseThrow(() -> new CustomEntityNotFoundException(ENTITY_NAME));

        Long ownerId = educationRepository.findMemberIdByEducationId(educationId)
			.orElseThrow(() -> new CustomEntityNotFoundException("소유자"));

        assertUtil.assertId(id, ownerId, ENTITY_NAME, action);

        educationRepository.delete(education);
		
	}
	
	

}
