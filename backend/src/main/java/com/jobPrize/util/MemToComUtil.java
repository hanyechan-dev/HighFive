package com.jobPrize.util;

import java.util.List;

import org.springframework.stereotype.Component;

import com.jobPrize.entity.memToCom.Application;
import com.jobPrize.entity.memToCom.Proposal;
import com.jobPrize.entity.memToCom.Similarity;
import com.jobPrize.entity.member.Career;
import com.jobPrize.entity.member.Education;
import com.jobPrize.entity.member.Member;
import com.jobPrize.enumerate.EducationLevel;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MemToComUtil {


    public boolean hasCareer(Object obj) {
        Member member = extractMember(obj);
        return member.getCareers().size() > 0;
    }

    public EducationLevel latestEducationLevel(Object obj) {
        Member member = extractMember(obj);
        List<Education> educations = member.getEducations();
        if (educations.isEmpty()) return EducationLevel.NONE;
        return educations.get(educations.size() - 1).getEducationLevel();
    }
    
    public String job(Object obj) {
    	 Member member = extractMember(obj);
    	 List<Career> careers =  member.getCareers();
    	 int latestCareerNumber = careers.size();
    	 if (careers.isEmpty()) return "";
    	 return member.getCareers().get(latestCareerNumber-1).getJob();
    	 
    }

    private Member extractMember(Object obj) {
        if (obj instanceof Application app) return app.getMember();
        if (obj instanceof Proposal proposal) return proposal.getMember();
        if (obj instanceof Similarity sim) return sim.getMember();
        if (obj instanceof Member member) return member;
        throw new IllegalArgumentException("지원하지 않는 타입입니다");
    }

}