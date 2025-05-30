package com.jobPrize.service.memToCom.pass;

import com.jobPrize.enumerate.ApprovalStatus;
import com.jobPrize.enumerate.UserType;

public interface PassService {
	void createPass(Long id, UserType userType, ApprovalStatus approvalStatus, boolean isSubscribed, Long applicationId);
}
