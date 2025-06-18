package com.jobPrize.service.member.coverLetterContent;

import com.jobPrize.dto.member.coverLetter.CoverLetterContentCreateDto;
import com.jobPrize.dto.member.coverLetter.CoverLetterContentResponseDto;
import com.jobPrize.dto.member.coverLetter.CoverLetterContentUpdateDto;
import com.jobPrize.entity.member.CoverLetter;

public interface CoverLetterContentService {
    CoverLetterContentResponseDto createCoverLetterContent(CoverLetter coverLetter, CoverLetterContentCreateDto coverLetterContentCreateDto);
    CoverLetterContentResponseDto updateCoverLetterContent(CoverLetterContentUpdateDto coverLetterContentUpdateDto);
    void deleteCoverLetterContent(Long coverLetterContentId);
}
