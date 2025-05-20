package com.jobPrize.service.member.certification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.entity.member.Certification;
import com.jobPrize.entity.member.Member;
import com.jobPrize.memberService.dto.certification.CertificationCreateDto;
import com.jobPrize.memberService.dto.certification.CertificationResponseDto;
import com.jobPrize.memberService.dto.certification.CertificationUpdateDto;
import com.jobPrize.repository.member.certification.CertificationRepository;
import com.jobPrize.repository.member.member.MemberRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CertificationServiceImpl implements CertificationService {

    private final CertificationRepository certificationRepository;

    private final MemberRepository memberRepository;

	@Override
	public void createCertification(Long id, CertificationCreateDto certificationCreateDto) {
        
		Member member = memberRepository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException("존재하지 않는 회원입니다."));

		Certification certification = Certification.of(member, certificationCreateDto);

		certificationRepository.save(certification);
	}

	@Override
	public List<CertificationResponseDto> getCertificationList(Long id) {

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
			.orElseThrow(() -> new EntityNotFoundException("존재하지 않는 자격증입니다."));

        if (!certification.getMember().getId().equals(id)) {
            throw new AccessDeniedException("자격증의 대상과 회원이 일치하지 않습니다.");
        }
        
		certification.updateCertification(certificationUpdateDto);

	}

	@Override
	public void deleteCertification(Long id, Long certificationId) {
        
        Certification certification = certificationRepository.findById(certificationId)
            .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 자격증입니다."));

        if (!certification.getMember().getId().equals(id)) {
            throw new AccessDeniedException("자격증의 대상과 회원이 일치하지 않습니다.");
        }

        certificationRepository.delete(certification);
		
	}
	

}
