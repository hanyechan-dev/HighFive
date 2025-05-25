package com.jobPrize.service.member.certification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.customException.CustomEntityNotFoundException;
import com.jobPrize.dto.member.certification.CertificationCreateDto;
import com.jobPrize.dto.member.certification.CertificationResponseDto;
import com.jobPrize.dto.member.certification.CertificationUpdateDto;
import com.jobPrize.entity.common.UserType;
import com.jobPrize.entity.member.Certification;
import com.jobPrize.entity.member.Member;
import com.jobPrize.repository.member.certification.CertificationRepository;
import com.jobPrize.repository.member.member.MemberRepository;
import com.jobPrize.util.AssertUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CertificationServiceImpl implements CertificationService {

    private final CertificationRepository certificationRepository;

    private final MemberRepository memberRepository;

	private final AssertUtil assertUtil;

	@Override
	public void createCertification(Long id, UserType userType, CertificationCreateDto certificationCreateDto) {
        
		assertUtil.assertUserType(userType, UserType.일반회원, "자격증 등록");

		Member member = memberRepository.findById(id)
			.orElseThrow(() -> new CustomEntityNotFoundException("회원"));

		Certification certification = Certification.of(member, certificationCreateDto);

		certificationRepository.save(certification);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CertificationResponseDto> readCertificationList(Long id) {

		List<Certification> certifications = certificationRepository.findAllByMemberId(id);
		List<CertificationResponseDto> certificationResponseDtos = new ArrayList<CertificationResponseDto>();

		for (Certification certification : certifications) {
			certificationResponseDtos.add(CertificationResponseDto.from(certification));
		}
		return certificationResponseDtos;
	}

	@Override
	public void updateCertification(Long id, CertificationUpdateDto certificationUpdateDto) {
        Long certificationId = certificationUpdateDto.getId();

		Certification certification = certificationRepository.findById(certificationId)
			.orElseThrow(() -> new CustomEntityNotFoundException("자격증"));

		assertUtil.assertId(id, certification, "수정");
        
		certification.updateCertification(certificationUpdateDto);

	}

	@Override
	public void deleteCertification(Long id, Long certificationId) {
        
        Certification certification = certificationRepository.findById(certificationId)
            .orElseThrow(() -> new CustomEntityNotFoundException("자격증"));

        assertUtil.assertId(id, certification, "삭제");

        certificationRepository.delete(certification);
		
	}
	

}
