package com.jobPrize.service.memToCom.pass;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.customException.CustomEntityNotFoundException;
import com.jobPrize.entity.memToCom.Application;
import com.jobPrize.entity.memToCom.Pass;
import com.jobPrize.enumerate.ApprovalStatus;
import com.jobPrize.enumerate.UserType;
import com.jobPrize.repository.memToCom.application.ApplicationRepository;
import com.jobPrize.repository.memToCom.pass.PassRepository;
import com.jobPrize.util.AssertUtil;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PassServiceImpl implements PassService {

	private final ApplicationRepository applicationRepository;

	private final PassRepository passRepository;

	private final AssertUtil assertUtil;

	private final static String ENTITY_NAME = "합격";

	@Override
	public void createPass(Long id, UserType userType, ApprovalStatus approvalStatus, boolean isSubscribed, Long applicationId) {

		String action = "수행";

		assertUtil.assertForCompany(userType, approvalStatus, isSubscribed, ENTITY_NAME, action);

		if (passRepository.existsByApplicationId(applicationId)) {
			throw new IllegalArgumentException("이미 합격된 지원서입니다.");
		}

		Application application = applicationRepository.findById(applicationId)
				.orElseThrow(() -> new CustomEntityNotFoundException("지원서"));

		Long ownerId = applicationRepository.findCompanyIdByApplicationId(applicationId)
				.orElseThrow(() -> new CustomEntityNotFoundException("소유자"));

		assertUtil.assertId(id, ownerId, ENTITY_NAME, action);

		Pass pass = Pass.builder().application(application).build();

		passRepository.save(pass);
	}


}
