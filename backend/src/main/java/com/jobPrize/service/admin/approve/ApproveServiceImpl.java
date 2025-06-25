package com.jobPrize.service.admin.approve;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.customException.CustomEntityNotFoundException;
import com.jobPrize.customException.CustomIllegalArgumentException;
import com.jobPrize.entity.common.User;
import com.jobPrize.enumerate.UserType;
import com.jobPrize.repository.common.user.UserRepository;
import com.jobPrize.util.AssertUtil;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ApproveServiceImpl implements ApproveService {

	private final UserRepository userRepository;
	
	private final AssertUtil assertUtil;

	private final static String ENTITY_NAME = "유저";

	private final static UserType ALLOWED_USER_TYPE = UserType.관리자;
	

	@Override
	public void approveUser(UserType userType, Long targetUserId) {

		String action = "승인";

		assertUtil.assertUserType(userType,ALLOWED_USER_TYPE,ENTITY_NAME,action);
		
		User user = userRepository.findByIdAndDeletedDateIsNull(targetUserId)
				.orElseThrow(() -> new CustomEntityNotFoundException("유저"));

		if (UserType.일반회원.equals(user.getUserType()) || UserType.관리자.equals(user.getUserType())) {
			throw new CustomIllegalArgumentException("승인 할 수 없는 회원입니다");
			
		}
		
		user.approve();
		userRepository.save(user);

	}
	
	
	@Override
	public void rejectUser(UserType userType, Long targetUserId) {

		String action = "거절";

		assertUtil.assertUserType(userType,ALLOWED_USER_TYPE,ENTITY_NAME,action);

		User user = userRepository.findByIdAndDeletedDateIsNull(targetUserId)
				.orElseThrow(() -> new CustomEntityNotFoundException("유저"));

		if (UserType.일반회원.equals(user.getUserType()) || UserType.관리자.equals(user.getUserType())) {
			throw new CustomIllegalArgumentException("거절 할 수 없는 회원입니다");
		}
		
		user.reject();
		userRepository.save(user);
		
	}
	

}
