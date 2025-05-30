package com.jobPrize.util;

import org.springframework.stereotype.Component;

import com.jobPrize.customException.CustomAccessDeniedException;
import com.jobPrize.customException.CustomApprovalDeniedException;
import com.jobPrize.customException.CustomOwnerMismatchException;
import com.jobPrize.customException.CustomSubscriptionDeniedException;
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

	public void assertId(Long id, Long ownerId, String targetEntityName, String action) {
		if (!ownerId.equals(id)) {
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


}
