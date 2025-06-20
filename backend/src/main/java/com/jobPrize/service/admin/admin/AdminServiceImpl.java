package com.jobPrize.service.admin.admin;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobPrize.customException.CustomEntityNotFoundException;
import com.jobPrize.dto.admin.service.CountByDayDto;
import com.jobPrize.dto.admin.service.CountByMonthDto;
import com.jobPrize.dto.admin.service.SubsCountDto;
import com.jobPrize.dto.admin.service.UserCountDto;
import com.jobPrize.entity.admin.Admin;
import com.jobPrize.entity.common.User;
import com.jobPrize.enumerate.UserType;
import com.jobPrize.repository.admin.admin.AdminRepository;
import com.jobPrize.repository.common.service.ServiceRepository;
import com.jobPrize.repository.common.user.UserRepository;
import com.jobPrize.util.AssertUtil;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
	
	private final UserRepository userRepository;
	
	private final AdminRepository adinRepository;
	
	private final ServiceRepository serviceRepository;

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
	
	// 지정된 기간 내 발생한 회원가입, 회원탈퇴 수를 사용자 유형에 따라 날짜(day)별로 조회
	@Override
	public List<CountByDayDto> countAllSignUpAndWithdrawalByDay(int days, UserType userType){
		List<CountByDayDto> dtoList = serviceRepository.countSignUpAndWithDrawalByDay(days, userType);
		return dtoList;
	}
	
	// 지정된 기간 내 발생한 회원가입, 회원탈퇴 수를 사용자 유형에 따라 월(month)별로 조회
	@Override
	public List<CountByMonthDto> countAllSignUpAndWithdrawalByMonth(int months, UserType userType){
		List<CountByMonthDto> dtoList = serviceRepository.countSignUpAndWithDrawalByMonth(months, userType);
		return dtoList;
	}
	
	// 사용자 유형에 따른 구독자, 비구독자 수를 취합하여 반환
	@Override
	public List<SubsCountDto> countAllSubsWithUserType(){
		SubsCountDto userDto = serviceRepository.countSubsByUserType(UserType.일반회원);
		SubsCountDto companyDto = serviceRepository.countSubsByUserType(UserType.기업회원);
		
		return List.of(userDto, companyDto);
	}
	
	// 각 UserType에 대한 총 회원을 List로 취합하여 반환
	@Override
	public List<UserCountDto> countAllUsersWithUserType(){
		UserCountDto userDto = serviceRepository.countUsersByUserType(UserType.일반회원);
		UserCountDto companyDto = serviceRepository.countUsersByUserType(UserType.기업회원);
		UserCountDto consultantDto = serviceRepository.countUsersByUserType(UserType.컨설턴트회원);
		
		return List.of(userDto, companyDto, consultantDto);
	}
}
