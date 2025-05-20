package com.jobPrize.memberService.service.coverLetterContent;

import com.jobPrize.entity.member.CoverLetter;
import com.jobPrize.memberService.dto.coverLetter.CoverLetterContentCreateDto;
import com.jobPrize.memberService.dto.coverLetter.CoverLetterContentUpdateDto;

public interface CoverLetterContentService {
    void createCoverLetterContent(CoverLetter coverLetter, CoverLetterContentCreateDto coverLetterContentCreateDto);
    void updateCoverLetterContent(CoverLetterContentUpdateDto coverLetterContentUpdateDto);
    void deleteCoverLetterContent(Long coverLetterContentId);
}
