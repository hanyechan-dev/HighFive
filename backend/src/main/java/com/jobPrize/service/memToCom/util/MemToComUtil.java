package com.jobPrize.service.memToCom.util;

import java.util.List;

import org.springframework.stereotype.Component;

import com.jobPrize.entity.company.Company;
import com.jobPrize.entity.memToCom.Application;
import com.jobPrize.entity.memToCom.EducationLevel;
import com.jobPrize.entity.memToCom.Proposal;
import com.jobPrize.entity.memToCom.Similarity;
import com.jobPrize.entity.member.Education;
import com.jobPrize.entity.member.Member;
import com.jobPrize.repository.memToCom.interest.InterestRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MemToComUtil {

    private final InterestRepository interestRepository;

    public boolean hasCareer(Object obj) {
        Member member = extractMember(obj);
        return member.getCareers().size() > 0;
    }

    public EducationLevel latestEducationLevel(Object obj) {
        Member member = extractMember(obj);
        List<Education> educations = member.getEducations();
        if (educations.isEmpty()) throw new IllegalStateException("교육 정보 없음");
        return educations.get(educations.size() - 1).getEducationLevel();
    }

    public boolean isInterested(Object obj) {
        Member member = extractMember(obj);
        Company company = extractCompany(obj);
        return interestRepository.existsByCompanyIdAndMemberId(company.getId(), member.getId());
    }
    
    public String job(Object obj) {
    	 Member member = extractMember(obj);
    	 int latestCareerNumber = member.getCareers().size();
    	 return member.getCareers().get(latestCareerNumber-1).getJob();
    	 
    }

    private Member extractMember(Object obj) {
        if (obj instanceof Application app) return app.getMember();
        if (obj instanceof Proposal proposal) return proposal.getMember();
        if (obj instanceof Similarity sim) return sim.getMember();
        throw new IllegalArgumentException("지원하지 않는 타입입니다");
    }

    private Company extractCompany(Object obj) {
        if (obj instanceof Application app) return app.getJobPosting().getCompany();
        if (obj instanceof Proposal proposal) return proposal.getCompany();
        if (obj instanceof Similarity sim) return sim.getJobPosting().getCompany();
        throw new IllegalArgumentException("지원하지 않는 타입입니다");
    }
}