package com.jobPrize.service.memToCom.pass;

import com.jobPrize.entity.common.UserType;

public interface PassService {
	void createPass(Long id, UserType userType, Long applicationId);
}
