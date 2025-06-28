package com.jobPrize.util;

import org.springframework.stereotype.Component;

import com.jobPrize.dto.company.jobPosting.JobPostingCreateDto;
import com.jobPrize.dto.company.jobPosting.JobPostingUpdateDto;
import com.jobPrize.entity.company.JobPosting;
import com.jobPrize.entity.member.Career;
import com.jobPrize.entity.member.CareerDescription;
import com.jobPrize.entity.member.CareerDescriptionContent;
import com.jobPrize.entity.member.Certification;
import com.jobPrize.entity.member.CoverLetter;
import com.jobPrize.entity.member.CoverLetterContent;
import com.jobPrize.entity.member.Education;
import com.jobPrize.entity.member.LanguageTest;
import com.jobPrize.entity.member.Member;

@Component
public class TextBuilder {
	public String getJobPostingStringForEmbedding(JobPostingCreateDto dto) {
        StringBuilder sb = new StringBuilder();

        sb.append("[채용공고 정보]\n");
        sb.append(String.format("- 공고명: %s\n", dto.getTitle()));
        sb.append(String.format("- 모집 부문: %s\n", dto.getJob()));
        sb.append(String.format("- 근무지: %s\n", dto.getWorkLocation()));
        sb.append(String.format("- 근무 시간: %s\n", dto.getWorkingHours()));
        sb.append(String.format("- 경력 조건: %s\n", dto.getCareerType()));
        sb.append(String.format("- 요구 학력: %s\n", dto.getEducationLevel()));
        sb.append(String.format("- 급여: %d\n 만원", dto.getSalary()));

        sb.append("\n[자격 요건]\n");
        sb.append(dto.getRequirement()).append("\n");

        if (dto.getContent() != null && !dto.getContent().isBlank()) {
            sb.append("\n[추가 내용]\n");
            sb.append(dto.getContent());
        }

        return sb.toString();
    }
	
	public String getJobPostingStringForEmbedding(JobPostingUpdateDto dto) {
        StringBuilder sb = new StringBuilder();

        sb.append("[채용공고 정보]\n");
        sb.append(String.format("- 공고명: %s\n", dto.getTitle()));
        sb.append(String.format("- 모집 부문: %s\n", dto.getJob()));
        sb.append(String.format("- 근무지: %s\n", dto.getWorkLocation()));
        sb.append(String.format("- 근무 시간: %s\n", dto.getWorkingHours()));
        sb.append(String.format("- 경력 조건: %s\n", dto.getCareerType()));
        sb.append(String.format("- 요구 학력: %s\n", dto.getEducationLevel()));
        sb.append(String.format("- 급여: %d\n 만원", dto.getSalary()));

        sb.append("\n[자격 요건]\n");
        sb.append(dto.getRequirement()).append("\n");

        if (dto.getContent() != null && !dto.getContent().isBlank()) {
            sb.append("\n[추가 내용]\n");
            sb.append(dto.getContent());
        }

        return sb.toString();
    }
	
	public String getJobPostingStringForEmbedding(JobPosting jobPosting) {
        StringBuilder sb = new StringBuilder();

        sb.append("[채용공고 정보]\n");
        sb.append(String.format("- 공고명: %s\n", jobPosting.getTitle()));
        sb.append(String.format("- 모집 부문: %s\n", jobPosting.getJob()));
        sb.append(String.format("- 근무지: %s\n", jobPosting.getWorkLocation()));
        sb.append(String.format("- 근무 시간: %s\n", jobPosting.getWorkingHours()));
        sb.append(String.format("- 경력 조건: %s\n", jobPosting.getCareerType()));
        sb.append(String.format("- 요구 학력: %s\n", jobPosting.getEducationLevel()));
        sb.append(String.format("- 급여: %d\n 만원", jobPosting.getSalary()));

        sb.append("\n[자격 요건]\n");
        sb.append(jobPosting.getRequirement()).append("\n");

        if (jobPosting.getContent() != null && !jobPosting.getContent().isBlank()) {
            sb.append("\n[추가 내용]\n");
            sb.append(jobPosting.getContent());
        }

        return sb.toString();
    }
	
	public String getMemberStringForEmbedding(Member member) {
	    StringBuilder sb = new StringBuilder();
	    sb.append("[이력서 정보]\n");

	    // 학력
	    sb.append("▶ 학력\n");
	    if (member.getEducations() == null || member.getEducations().isEmpty()) {
	        sb.append("- 학력 정보 없음\n");
	    } else {
	        for (Education edu : member.getEducations()) {
	            sb.append(String.format(
	                "- %s %s / GPA: %s / 재학기간: %s ~ %s\n",
	                edu.getSchoolName(),
	                edu.getMajor(),
	                edu.getGpa(),
	                edu.getEnterDate(),
	                edu.getGraduateDate()
	            ));
	        }
	    }

	    // 경력
	    sb.append("\n▶ 경력\n");
	    if (member.getCareers() == null || member.getCareers().isEmpty()) {
	        sb.append("- 경력 정보 없음\n");
	    } else {
	        for (Career career : member.getCareers()) {
	            sb.append(String.format(
	                "- %s / %s / %s / %s / %s ~ %s\n",
	                career.getCompanyName(),
	                career.getJob(),
	                career.getDepartment(),
	                career.getPosition(),
	                career.getStartDate(),
	                career.getEndDate()
	            ));
	        }
	    }

	    // 자격증
	    sb.append("\n▶ 자격증\n");
	    if (member.getCertifications() == null || member.getCertifications().isEmpty()) {
	        sb.append("- 자격증 정보 없음\n");
	    } else {
	        for (Certification cert : member.getCertifications()) {
	            sb.append(String.format(
	                "- %s (%s) / 취득일: %s\n",
	                cert.getCertificationName(),
	                cert.getIssuingOrg(),
	                cert.getAcquisitionDate()
	            ));
	        }
	    }

	    // 어학시험
	    sb.append("\n▶ 어학시험\n");
	    if (member.getLanguageTests() == null || member.getLanguageTests().isEmpty()) {
	        sb.append("- 어학시험 정보 없음\n");
	    } else {
	        for (LanguageTest lang : member.getLanguageTests()) {
	            sb.append(String.format(
	                "- %s / 점수: %s / 시험일: %s\n",
	                lang.getTestName(),
	                lang.getScore(),
	                lang.getAcquisitionDate()
	            ));
	        }
	    }

	    // 경력기술서
	    sb.append("\n[경력기술서]\n");
	    if (member.getCareerDescriptions() == null || member.getCareerDescriptions().isEmpty()) {
	        sb.append("- 경력기술서 정보 없음\n");
	    } else {
	        for (CareerDescription cd : member.getCareerDescriptions()) {
	            if (cd.getCareerDescriptionContents() == null || cd.getCareerDescriptionContents().isEmpty()) {
	                sb.append("- 상세 내용 없음\n");
	            } else {
	                for (CareerDescriptionContent cdc : cd.getCareerDescriptionContents()) {
	                    sb.append(String.format(
	                        "- 항목: %s, 내용: %s\n",
	                        cdc.getItem(),
	                        cdc.getContent()
	                    ));
	                }
	            }
	        }
	    }

	    // 자기소개서
	    sb.append("\n[자기소개서]\n");
	    if (member.getCoverLetters() == null || member.getCoverLetters().isEmpty()) {
	        sb.append("- 자기소개서 정보 없음\n");
	    } else {
	        for (CoverLetter cl : member.getCoverLetters()) {
	            if (cl.getCoverLetterContents() == null || cl.getCoverLetterContents().isEmpty()) {
	                sb.append("- 상세 내용 없음\n");
	            } else {
	                for (CoverLetterContent clc : cl.getCoverLetterContents()) {
	                    sb.append(String.format(
	                        "- 항목: %s, 내용: %s\n",
	                        clc.getItem(),
	                        clc.getContent()
	                    ));
	                }
	            }
	        }
	    }

	    return sb.toString();
	}
}


