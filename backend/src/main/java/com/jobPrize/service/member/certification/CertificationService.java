package com.jobPrize.service.member.certification;

import java.util.List;

import com.jobPrize.dto.member.certification.CertificationCreateDto;
import com.jobPrize.dto.member.certification.CertificationResponseDto;
import com.jobPrize.dto.member.certification.CertificationUpdateDto;
import com.jobPrize.enumerate.UserType;

public interface CertificationService {

    public CertificationResponseDto createCertification(Long id, UserType userType, CertificationCreateDto certificationCreateDto);
    
    public List<CertificationResponseDto> readCertificationList(Long id);
    
    public CertificationResponseDto updateCertification(Long id, CertificationUpdateDto certificationUpdateDto);
    
    public void deleteCertification(Long id, Long certificationId);
}
