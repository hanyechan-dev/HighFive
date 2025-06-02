package com.jobPrize.service.admin.admin;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.customException.CustomEntityNotFoundException;
import com.jobPrize.entity.admin.Admin;
import com.jobPrize.entity.common.User;
import com.jobPrize.enumerate.UserType;
import com.jobPrize.repository.admin.admin.AdminRepository;
import com.jobPrize.repository.common.user.UserRepository;
import com.jobPrize.util.AssertUtil;

import lombok.RequiredArgsConstructor;


@Service
@Transactional
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
	
	private final UserRepository userRepository;
	
	private final AdminRepository adinRepository;

	private final AssertUtil assertUtil;

	private final static String ENTITY_NAME = "관리자";

	private final static UserType ALLOWED_USER_TYPE = UserType.관리자;

	@Override
	public void createAdmin(Long id, UserType userType) {
		
		String action = "등록";

		assertUtil.assertUserType(userType, ALLOWED_USER_TYPE, ENTITY_NAME, action);

		User user = userRepository.findByIdAndDeletedDateIsNull(id)
				.orElseThrow(() -> new CustomEntityNotFoundException(ENTITY_NAME));
		Admin admin = Admin.builder().user(user).build();
		adinRepository.save(admin);
		
	}


}
