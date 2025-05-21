package com.jobPrize.service.admin.approve;

import com.jobPrize.entity.common.UserType;

public interface ApproveService {

	void approveUser(UserType userType, Long targetUserId);
	
	void rejectUser(UserType userType, Long targetUserId);

}
