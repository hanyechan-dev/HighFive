package com.jobPrize.Admin02.service.service.userApprove;

import java.util.List;

import com.jobPrize.entity.common.User;

public interface ApproveService {

	void singUpApproveUser(Long userId, String token);

	void singUpRejectUser(Long userId, String token);

	List<User> waitingUsers();

	List<User> approvedUsersCheck();

	List<User> rejectedUsersCheck();
}
