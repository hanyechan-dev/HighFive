package com.jobPrize.util;

import org.springframework.stereotype.Component;

import com.jobPrize.customException.CustomAccessDeniedException;
import com.jobPrize.customException.CustomApprovalDeniedException;
import com.jobPrize.customException.CustomOwnerMismatchException;
import com.jobPrize.customException.CustomSubscriptionDeniedException;
import com.jobPrize.entity.common.Post;
import com.jobPrize.entity.common.User;
import com.jobPrize.entity.company.Company;
import com.jobPrize.entity.company.JobPosting;
import com.jobPrize.entity.company.Schedule;
import com.jobPrize.entity.consultant.AiConsulting;
import com.jobPrize.entity.consultant.ConsultantConsulting;
import com.jobPrize.entity.memToCom.Application;
import com.jobPrize.entity.memToCon.Request;
import com.jobPrize.entity.member.Career;
import com.jobPrize.entity.member.CareerDescription;
import com.jobPrize.entity.member.Certification;
import com.jobPrize.entity.member.CoverLetter;
import com.jobPrize.entity.member.Education;
import com.jobPrize.entity.member.LanguageTest;
import com.jobPrize.entity.member.Member;
import com.jobPrize.enumerate.ApprovalStatus;
import com.jobPrize.enumerate.UserType;

@Component
public class AssertUtil {
	
	public void assertForConsultant(UserType userType, ApprovalStatus approvalStatus, String action) {
		
		UserType allowedUserType = UserType.컨설턴트회원;
		
		assertUserType(userType, allowedUserType, action);
		assertApprovalStatus(approvalStatus, allowedUserType, action);
	}
	
	
	public void assertForCompany(UserType userType, ApprovalStatus approvalStatus, boolean isSubscribed ,String action) {
		
		UserType allowedUserType = UserType.기업회원;
		
		assertUserType(userType, allowedUserType, action);
		assertApprovalStatus(approvalStatus, allowedUserType, action);
		assertSubscription(allowedUserType, isSubscribed, action);
	}
	
	
	

	public void assertUserType(UserType userType, UserType allowedUserType, String action) {
		if (!allowedUserType.equals(userType)) {
			throw new CustomAccessDeniedException(allowedUserType, action);
		}
	}

	public void assertUserType(UserType userType, UserType allowedUserType, UserType allowedUserType2, String action) {
		if (!allowedUserType.equals(userType) && !allowedUserType2.equals(userType)) {
			throw new CustomAccessDeniedException(allowedUserType, allowedUserType2, action);
		}
	}

	public void assertId(Long id, Object obj, String action) {
		Long ownerId = extractUserId(obj);
		if (!ownerId.equals(id)) {
			String targetEntityName = obj.getClass().getSimpleName();
			throw new CustomOwnerMismatchException(targetEntityName, action);
		}
	}

	private void assertApprovalStatus(ApprovalStatus approvalStatus, UserType allowedUserType, String action) {
		if (!ApprovalStatus.APPROVED.equals(approvalStatus)) {
			throw new CustomApprovalDeniedException(allowedUserType, action);

		}
	}

	public void assertSubscription(UserType allowedUserType, boolean isSubscribed, String action) {
		
		if (!isSubscribed) {
			throw new CustomSubscriptionDeniedException(allowedUserType, action);

		}
	}

	private Long extractUserId(Object obj) {
		if (obj instanceof Post post)
			return post.getUser().getId();
		if (obj instanceof User user)
			return user.getId();
		if (obj instanceof Company company)
			return company.getId();
		if (obj instanceof Member member)
			return member.getId();
		if (obj instanceof JobPosting jobPosting)
			return jobPosting.getCompany().getId();
		if (obj instanceof Schedule schedule)
			return schedule.getCompany().getId();
		if (obj instanceof Request request)
			return request.getMember().getId();
		if (obj instanceof AiConsulting aiConsulting)
			return aiConsulting.getConsultantConsulting().getConsultant().getId();
		if (obj instanceof ConsultantConsulting consultantConsulting)
			return consultantConsulting.getConsultant().getId();
		if (obj instanceof Education education)
			return education.getMember().getId();
		if (obj instanceof Career career)
			return career.getMember().getId();
		if (obj instanceof Certification certification)
			return certification.getMember().getId();
		if (obj instanceof LanguageTest languageTest)
			return languageTest.getMember().getId();
		if (obj instanceof CoverLetter coverLetter)
			return coverLetter.getMember().getId();
		if (obj instanceof CareerDescription careerDescription)
			return careerDescription.getMember().getId();
		if (obj instanceof Application application)
			return application.getJobPosting().getCompany().getId();

		throw new IllegalArgumentException("지원하지 않는 타입입니다");
	}

}
