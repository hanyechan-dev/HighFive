package com.jobPrize.service.admin.approve;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.customException.CustomEntityNotFoundException;
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
	

	@Override
	public void approveUser(UserType userType, Long targetUserId) {

		assertUtil.assertUserType(userType,UserType.관리자,"승인");
		
		User user = userRepository.findByIdAndDeletedDateIsNull(targetUserId)
				.orElseThrow(() -> new CustomEntityNotFoundException("유저"));

		if (user.getType() == UserType.일반회원 || user.getType() == UserType.관리자) {
			throw new IllegalArgumentException("승인 할 수 없는 회원입니다");
			
		}
		
		user.approve();
		userRepository.save(user);

	}
	
	
	@Override
	public void rejectUser(UserType userType, Long targetUserId) {

		assertUtil.assertUserType(userType,UserType.관리자,"거절");

		User user = userRepository.findByIdAndDeletedDateIsNull(targetUserId)
				.orElseThrow(() -> new CustomEntityNotFoundException("유저"));

		if (user.getType() == UserType.일반회원 || user.getType() == UserType.관리자 ) {
			throw new IllegalArgumentException("거절 할 수 없는 회원입니다");
		}
		
		user.reject();
		userRepository.save(user);
		
	}
	

}
