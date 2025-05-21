package com.jobPrize.service.member.certification;

import java.util.List;

import com.jobPrize.dto.member.certification.CertificationCreateDto;
import com.jobPrize.dto.member.certification.CertificationResponseDto;
import com.jobPrize.dto.member.certification.CertificationUpdateDto;

public interface CertificationService {
    public void createCertification(Long id, CertificationCreateDto certificationCreateDto);
    public List<CertificationResponseDto> readCertificationList(Long id);
    public void updateCertification(Long id, CertificationUpdateDto certificationUpdateDto);
    public void deleteCertification(Long id, Long certificationId);
}
