package com.jobPrize.service.member.education;

import java.util.List;

import com.jobPrize.dto.member.education.EducationCreateDto;
import com.jobPrize.dto.member.education.EducationResponseDto;
import com.jobPrize.dto.member.education.EducationUpdateDto;

public interface EducationService {
    public void createEducation(Long id, EducationCreateDto educationCreateDto);
    public List<EducationResponseDto> readEducationList(Long id);
    public void updateEducation(Long id, EducationUpdateDto educationUpdateDto);
    public void deleteEducation(Long id, Long educationId);
}
