package com.jobPrize.service.member.education;

import java.util.List;

import com.jobPrize.dto.member.education.EducationCreateDto;
import com.jobPrize.dto.member.education.EducationResponseDto;
import com.jobPrize.dto.member.education.EducationUpdateDto;
import com.jobPrize.enumerate.UserType;

public interface EducationService {
    public EducationResponseDto createEducation(Long id, UserType userType, EducationCreateDto educationCreateDto);
    public List<EducationResponseDto> readEducationList(Long id);
    public EducationResponseDto updateEducation(Long id, EducationUpdateDto educationUpdateDto);
    public void deleteEducation(Long id, Long educationId);
}
