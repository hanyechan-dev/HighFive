package com.jobPrize.service.admin.approve;

import com.jobPrize.enumerate.UserType;

public interface ApproveService {

	void approveUser(UserType userType, Long targetUserId);
	
	void rejectUser(UserType userType, Long targetUserId);

}
