package com.jobPrize.service.member.coverLetter;

import java.util.List;

import com.jobPrize.memberService.dto.coverLetter.CoverLetterCreateDto;
import com.jobPrize.memberService.dto.coverLetter.CoverLetterResponseDto;
import com.jobPrize.memberService.dto.coverLetter.CoverLetterSummaryDto;
import com.jobPrize.memberService.dto.coverLetter.CoverLetterUpdateDto;

public interface CoverLetterService {

	void createCoverLetter(Long id, CoverLetterCreateDto coverLetterCreateDto);
    List<CoverLetterSummaryDto> getCoverLetterList(Long id);
    CoverLetterResponseDto getCoverLetter(Long id, Long coverLetterId);
    void updateCoverLetter(Long id, CoverLetterUpdateDto coverLetterUpdateDto);
    void deleteCoverLetter(Long id, Long coverLetterId);

}
