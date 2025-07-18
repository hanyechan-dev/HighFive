package com.jobPrize.service.member.career;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.customException.CustomEntityNotFoundException;
import com.jobPrize.customException.CustomIllegalArgumentException;
import com.jobPrize.dto.member.career.CareerCreateDto;
import com.jobPrize.dto.member.career.CareerResponseDto;
import com.jobPrize.dto.member.career.CareerUpdateDto;
import com.jobPrize.entity.member.Career;
import com.jobPrize.entity.member.Member;
import com.jobPrize.enumerate.EmbeddingStatus;
import com.jobPrize.enumerate.UserType;
import com.jobPrize.repository.memToCom.similarity.SimilarityRepository;
import com.jobPrize.repository.member.career.CareerRepository;
import com.jobPrize.repository.member.member.MemberRepository;
import com.jobPrize.util.AssertUtil;
import com.jobPrize.util.TextBuilder;
import com.jobPrize.util.WebClientUtil;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CareerServiceImpl implements CareerService {
	
	private final CareerRepository careerRepository;

	private final MemberRepository memberRepository;
	
	private final SimilarityRepository similarityRepository;

	private final AssertUtil assertUtil;
	
	private final WebClientUtil webClientUtil;

	private final TextBuilder textBuilder;
	
	private final static String ENTITY_NAME = "경력";

	private final static UserType ALLOWED_USER_TYPE = UserType.일반회원;



	@Override
	public CareerResponseDto createCareer(Long id, UserType userType, CareerCreateDto careerCreateDto) {

		String action = "등록";

		assertUtil.assertUserType(userType, ALLOWED_USER_TYPE, ENTITY_NAME, action);

		if(careerCreateDto.getEndDate() != null && careerCreateDto.getStartDate() != null && careerCreateDto.getStartDate().isAfter(careerCreateDto.getEndDate())) {
			throw new CustomIllegalArgumentException("퇴사일은 입사일보다 빠를 수 없습니다.");
		}

		Member member = memberRepository.findByIdAndDeletedDateIsNull(id)
				.orElseThrow(() -> new CustomEntityNotFoundException("회원"));
		

		Career career = Career.of(member, careerCreateDto);
		
		careerRepository.save(career);
		
		updateEmbedding(career);
		
		member.changeLastUpdateTime();

		similarityRepository.deleteByMember(member);
		
		return CareerResponseDto.from(career);
		
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
	public CareerResponseDto updateCareer(Long id, CareerUpdateDto careerUpdateDto) {

		String action = "수정";
		
		if(careerUpdateDto.getStartDate().isAfter(careerUpdateDto.getEndDate())) {
			throw new CustomIllegalArgumentException("퇴사일은 입사일보다 빠를 수 없습니다.");
		}
		
		Long careerId = careerUpdateDto.getId();
		Career career =	careerRepository.findById(careerId)
				.orElseThrow(() -> new CustomEntityNotFoundException(ENTITY_NAME));

		Long ownerId = careerRepository.findMemberIdByCareerId(careerId)
				.orElseThrow(() -> new CustomEntityNotFoundException("소유자"));

		assertUtil.assertId(id, ownerId, ENTITY_NAME, action);
		
		career.updateCareer(careerUpdateDto);
		
		updateEmbedding(career);
		
		Member member = career.getMember();
		
		member.changeLastUpdateTime();
		
		similarityRepository.deleteByMember(member);
		
		return CareerResponseDto.from(career);
	}

	@Override
	public void deleteCareer(Long id, Long careerId) {

		String action = "삭제";
		
		Career career =	careerRepository.findById(careerId)
				.orElseThrow(() -> new CustomEntityNotFoundException(ENTITY_NAME));

		Long ownerId = careerRepository.findMemberIdByCareerId(careerId)
				.orElseThrow(() -> new CustomEntityNotFoundException("소유자"));

		assertUtil.assertId(id, ownerId, ENTITY_NAME, action);
		
		Member member = career.getMember();
		
		careerRepository.delete(career);
		
		similarityRepository.deleteByMember(member);
	}
	
	
	private void updateEmbedding(Career career) {
	    try {
	        career.updateEmbeddingStatus(EmbeddingStatus.PROCESSING);
	        String data = textBuilder.getCareerStringForEmbedding(career);
	        String vector = webClientUtil.sendEmbeddingRequestMember(data);
	        career.updateVector(vector);
	        career.updateEmbeddingStatus(EmbeddingStatus.SUCCESS);
	    } catch (Exception e) {
	        career.updateEmbeddingStatus(EmbeddingStatus.FAILED);
	    }
	}
		
}
