package com.jobPrize.service.company.memberPool;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.customException.CustomEntityNotFoundException;
import com.jobPrize.dto.company.memberPool.MemberFilterCondition;
import com.jobPrize.dto.company.memberPool.MemberPoolDetailDto;
import com.jobPrize.dto.company.memberPool.MemberPoolSummaryDto;
import com.jobPrize.dto.member.career.CareerResponseDto;
import com.jobPrize.dto.member.certification.CertificationResponseDto;
import com.jobPrize.dto.member.education.EducationResponseDto;
import com.jobPrize.dto.member.languageTest.LanguageTestResponseDto;
import com.jobPrize.entity.memToCom.Similarity;
import com.jobPrize.entity.member.Certification;
import com.jobPrize.entity.member.LanguageTest;
import com.jobPrize.entity.member.Member;
import com.jobPrize.enumerate.ApprovalStatus;
import com.jobPrize.enumerate.EducationLevel;
import com.jobPrize.enumerate.UserType;
import com.jobPrize.repository.memToCom.similarity.SimilarityRepository;
import com.jobPrize.repository.member.member.MemberRepository;
import com.jobPrize.util.AssertUtil;
import com.jobPrize.util.MemToComUtil;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberPoolServiceImpl implements MemberPoolService {

	private final SimilarityRepository similarityRepository;

	private final MemToComUtil memToComUtil;

	private final MemberRepository memberRepository;
	
	private final AssertUtil assertUtil;

	private static final String ENTITY_NAME = "인재";

	@Override
	@Transactional(readOnly = true)
	public Page<MemberPoolSummaryDto> readMemberPoolPageByCondition(Long id, UserType userType, ApprovalStatus approvalStatus, boolean isSubscribed,
			MemberFilterCondition memberFilterCondition, Pageable pageable) {

		String action = "조회";
		
		assertUtil.assertForCompany(userType, approvalStatus, isSubscribed, ENTITY_NAME, action);
		
		
		Page<Similarity> similarities = similarityRepository.findAllWithMemberByCompanyIdAndCondition(id,
				memberFilterCondition, pageable);
		List<MemberPoolSummaryDto> memberPoolSummaryDtos = new ArrayList<>();

		for (Similarity similarity : similarities) {
			boolean hasCareer = memToComUtil.hasCareer(similarity);
			String job = memToComUtil.job(similarity);
			EducationLevel latestEducationLevel = memToComUtil.latestEducationLevel(similarity);

			MemberPoolSummaryDto memberPoolSummaryDto = MemberPoolSummaryDto.of(similarity, hasCareer, job,
					latestEducationLevel);

			memberPoolSummaryDtos.add(memberPoolSummaryDto);
		}

		return new PageImpl<MemberPoolSummaryDto>(memberPoolSummaryDtos, pageable, similarities.getTotalElements());
	}

	@Override
	@Transactional(readOnly = true)
	public MemberPoolDetailDto readMemberPoolDetail(UserType userType, ApprovalStatus approvalStatus, boolean isSubscribed, Long memberId) {
		
		String action = "조회";
		
		assertUtil.assertForCompany(userType, approvalStatus, isSubscribed, ENTITY_NAME, action);

		Member member = memberRepository.findByIdAndDeletedDateIsNull(memberId)
				.orElseThrow(() -> new CustomEntityNotFoundException("회원"));

		boolean hasCareer = memToComUtil.hasCareer(member);

		String job = memToComUtil.job(member);

		EducationResponseDto educationResponseDto = null;
		if (member.getEducations() != null && !member.getEducations().isEmpty()) {
			educationResponseDto = EducationResponseDto
					.from(member.getEducations().get(member.getEducations().size() - 1));
		}

		CareerResponseDto careerResponseDto = null;
		if (member.getCareers() != null && !member.getCareers().isEmpty()) {
			careerResponseDto = CareerResponseDto.from(member.getCareers().get(member.getCareers().size() - 1));
		}

		List<CertificationResponseDto> certificationResponseDtos = new ArrayList<>();
		if (member.getCertifications() != null && !member.getCertifications().isEmpty()) {
			for (Certification certification : member.getCertifications()) {
				certificationResponseDtos.add(CertificationResponseDto.from(certification));
			}
		}

		List<LanguageTestResponseDto> languageTestResponseDtos = new ArrayList<>();
		if (member.getLanguageTests() != null && !member.getLanguageTests().isEmpty()) {
			for (LanguageTest languageTest : member.getLanguageTests()) {
				languageTestResponseDtos.add(LanguageTestResponseDto.from(languageTest));
			}
		}

		return MemberPoolDetailDto.of(member.getUser(), hasCareer, job, educationResponseDto, careerResponseDto,
				certificationResponseDtos, languageTestResponseDtos);
	}
}
