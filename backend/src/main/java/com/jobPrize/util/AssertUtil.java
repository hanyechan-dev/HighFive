package com.jobPrize.util;

import org.springframework.stereotype.Component;

import com.jobPrize.customException.CustomAccessDeniedException;
import com.jobPrize.customException.CustomOwnerMismatchException;
import com.jobPrize.entity.common.Post;
import com.jobPrize.entity.common.User;
import com.jobPrize.entity.common.UserType;
import com.jobPrize.entity.company.Company;
import com.jobPrize.entity.company.JobPosting;
import com.jobPrize.entity.company.Schedule;
import com.jobPrize.entity.consultant.AiConsulting;
import com.jobPrize.entity.consultant.ConsultantConsulting;
import com.jobPrize.entity.memToCon.Request;
import com.jobPrize.entity.member.Career;
import com.jobPrize.entity.member.CareerDescription;
import com.jobPrize.entity.member.Certification;
import com.jobPrize.entity.member.CoverLetter;
import com.jobPrize.entity.member.Education;
import com.jobPrize.entity.member.LanguageTest;
import com.jobPrize.entity.member.Member;


@Component
public class AssertUtil {
	
	public void assertUserType(UserType userType, UserType allowedUserType, String action) {
		if (!allowedUserType.equals(userType)) {
			throw new CustomAccessDeniedException(allowedUserType, action);
		}
	}
	
	public void assertUserType(UserType userType, UserType allowedUserType, UserType allowedUserType2, String action) {
		if(!allowedUserType.equals(userType)&&!allowedUserType2.equals(userType)){
			throw new CustomAccessDeniedException(allowedUserType, allowedUserType2,action);
		}
	}
	
	
	public void assertId(Long id, Object obj, String action) {
	    Long ownerId = extractUserId(obj);
	    if (!ownerId.equals(id)) {
	        String className = obj.getClass().getSimpleName();
	        throw new CustomOwnerMismatchException(className, action);
	    }
	}
	
	
	
	
	 private Long extractUserId(Object obj) {
	        if (obj instanceof Post post) return post.getUser().getId();
	        if (obj instanceof User user) return user.getId();
	        if (obj instanceof Company company) return company.getId();
	        if (obj instanceof Member member) return member.getId();
	        if (obj instanceof JobPosting jobPosting) return jobPosting.getCompany().getId();
	        if (obj instanceof Schedule schedule) return schedule.getCompany().getId();
	        if (obj instanceof Request request) return request.getMember().getId();
	        if (obj instanceof AiConsulting aiConsulting) return aiConsulting.getConsultantConsulting().getConsultant().getId();
	        if (obj instanceof ConsultantConsulting consultantConsulting) return consultantConsulting.getConsultant().getId();
	        if (obj instanceof Education education) return education.getMember().getId();
	        if (obj instanceof Career career) return career.getMember().getId();
	        if (obj instanceof Certification certification) return certification.getMember().getId();
			if (obj instanceof LanguageTest languageTest) return languageTest.getMember().getId();
			if (obj instanceof CoverLetter coverLetter) return coverLetter.getMember().getId();
	        if (obj instanceof CareerDescription careerDescription) return careerDescription.getMember().getId();

	        throw new IllegalArgumentException("지원하지 않는 타입입니다");
	   }

}
