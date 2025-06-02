package com.jobPrize.service.consultant.consultant;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.customException.CustomEntityNotFoundException;
import com.jobPrize.entity.common.User;
import com.jobPrize.entity.consultant.Consultant;
import com.jobPrize.enumerate.UserType;
import com.jobPrize.repository.common.user.UserRepository;
import com.jobPrize.repository.consultant.consultant.ConsultantRepository;
import com.jobPrize.util.AssertUtil;

import lombok.RequiredArgsConstructor;


@Service
@Transactional
@RequiredArgsConstructor
public class ConsultantServiceImpl implements ConsultantService {
	
	private final UserRepository userRepository;
	
	private final ConsultantRepository consultantRepository;

	private final AssertUtil assertUtil;

	private final static String ENTITY_NAME = "컨설턴트";

	private final static UserType ALLOWED_USER_TYPE = UserType.컨설턴트회원;

	@Override
	public void createConsultant(Long id, UserType userType) {
		
		String action = "등록";

		assertUtil.assertUserType(userType, ALLOWED_USER_TYPE, ENTITY_NAME, action);

		User user = userRepository.findByIdAndDeletedDateIsNull(id)
				.orElseThrow(() -> new CustomEntityNotFoundException(ENTITY_NAME));
		Consultant consultant = Consultant.builder().user(user).build();
		consultantRepository.save(consultant);
		
	}

}
