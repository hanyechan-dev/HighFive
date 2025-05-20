package com.jobPrize.service.member.certification;

import java.util.List;

import com.jobPrize.memberService.dto.certification.CertificationCreateDto;
import com.jobPrize.memberService.dto.certification.CertificationResponseDto;
import com.jobPrize.memberService.dto.certification.CertificationUpdateDto;

public interface CertificationService {
    public void createCertification(Long id, CertificationCreateDto certificationCreateDto);
    public List<CertificationResponseDto> getCertificationList(Long id);
    public void updateCertification(Long id, CertificationUpdateDto certificationUpdateDto);
    public void deleteCertification(Long id, Long certificationId);
}
