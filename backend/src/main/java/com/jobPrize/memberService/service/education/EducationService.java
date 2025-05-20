package com.jobPrize.memberService.service.education;

import java.util.List;

import com.jobPrize.memberService.dto.education.EducationCreateDto;
import com.jobPrize.memberService.dto.education.EducationResponseDto;
import com.jobPrize.memberService.dto.education.EducationUpdateDto;

public interface EducationService {
    public void createEducation(Long id, EducationCreateDto educationCreateDto);
    public List<EducationResponseDto> getEducationList(Long id);
    public void updateEducation(Long id, EducationUpdateDto educationUpdateDto);
    public void deleteEducation(Long id, Long educationId);
}
