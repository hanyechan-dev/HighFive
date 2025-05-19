package com.jobPrize.memberService.service.commom;

import com.jobPrize.memberService.dto.login.LogInDto;
import com.jobPrize.memberService.dto.myPage.MyPageResponseDto;
import com.jobPrize.memberService.dto.myPage.MyPageUpdateDto;
import com.jobPrize.memberService.dto.myPage.PasswordUpdateDto;
import com.jobPrize.memberService.dto.signUp.UserSignUpDto;
import com.jobPrize.memberService.dto.token.TokenDto;

public interface UserService {
	public TokenDto signUpUser(UserSignUpDto userSignUpDto);
//	public void registerCompanyInfo(CompanySignUpDto companySignUpAdditionalDto, String token);
	public TokenDto logIn(LogInDto logInDto);
	public MyPageResponseDto getUserMyPageInfo(String token);
	public void updateUserMyPageInfo(String token, MyPageUpdateDto myPageUpdateDto);
	public void updateUserPassword(String token, PasswordUpdateDto passwordUpdateDto);
	public void softDeleteUser(String token);
		
}
