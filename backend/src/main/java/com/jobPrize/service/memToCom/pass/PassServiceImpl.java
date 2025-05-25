package com.jobPrize.service.memToCom.pass;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.customException.CustomEntityNotFoundException;
import com.jobPrize.entity.memToCom.Application;
import com.jobPrize.entity.memToCom.Pass;
import com.jobPrize.repository.memToCom.application.ApplicationRepository;
import com.jobPrize.repository.memToCom.pass.PassRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PassServiceImpl implements PassService {
	
	private ApplicationRepository applicationRepository;
	
	private PassRepository passRepository;

	@Override
	public void createPass(Long applicationId) {
		Application application = applicationRepository.findById(applicationId)
				.orElseThrow(()->new CustomEntityNotFoundException("지원서"));
		
		Pass pass = Pass.builder()
			.application(application)
			.build();
		
		passRepository.save(pass);
	}

}
