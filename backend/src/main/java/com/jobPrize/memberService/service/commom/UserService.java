package com.jobPrize.memberService.service.commom;

import com.jobPrize.memberService.dto.login.LogInDto;
import com.jobPrize.memberService.dto.signUp.CompanySignUpDto;
import com.jobPrize.memberService.dto.signUp.MemberSignUpDto;
import com.jobPrize.memberService.dto.signUp.UserSignUpDto;

public interface UserService {
	public String signUpUser(UserSignUpDto userSignUpDto);
	public void registerCompanyInfo(CompanySignUpDto companySignUpAdditionalDto, String token);
	public void registerMemberInfo(MemberSignUpDto memberSignUpDto, String token);
	public String logIn(LogInDto logInDto);
		
}
