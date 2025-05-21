package com.jobPrize.Admin02.service.service.userApprove;

import java.util.List;

import com.jobPrize.entity.common.User;
import com.jobPrize.entity.common.UserType;

public interface ApproveService {

	void approveSingUpUser(UserType userType, Long targetUserId);
	
	void rejectSingUpUser(UserType userType, Long targetUserId);

	List<User> readWaitingUserList();

	List<User> readApprovedUserList();

	List<User> readRejectedUserList();
}
