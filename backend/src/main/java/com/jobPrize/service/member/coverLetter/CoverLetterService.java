package com.jobPrize.service.member.coverLetter;

import java.util.List;

import com.jobPrize.dto.member.coverLetter.CoverLetterCreateDto;
import com.jobPrize.dto.member.coverLetter.CoverLetterResponseDto;
import com.jobPrize.dto.member.coverLetter.CoverLetterSummaryDto;
import com.jobPrize.dto.member.coverLetter.CoverLetterUpdateDto;
import com.jobPrize.enumerate.UserType;

public interface CoverLetterService {

	CoverLetterResponseDto createCoverLetter(Long id, UserType userType, CoverLetterCreateDto coverLetterCreateDto);
    List<CoverLetterSummaryDto> readCoverLetterList(Long id);
    CoverLetterResponseDto readCoverLetter(Long id, Long coverLetterId);
    CoverLetterResponseDto updateCoverLetter(Long id, CoverLetterUpdateDto coverLetterUpdateDto);
    void deleteCoverLetter(Long id, Long coverLetterId);

}
