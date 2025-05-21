package com.jobPrize.service.member.coverLetterContent;

import com.jobPrize.dto.member.coverLetter.CoverLetterContentCreateDto;
import com.jobPrize.dto.member.coverLetter.CoverLetterContentUpdateDto;
import com.jobPrize.entity.member.CoverLetter;

public interface CoverLetterContentService {
    void createCoverLetterContent(CoverLetter coverLetter, CoverLetterContentCreateDto coverLetterContentCreateDto);
    void updateCoverLetterContent(CoverLetterContentUpdateDto coverLetterContentUpdateDto);
    void deleteCoverLetterContent(Long coverLetterContentId);
}
