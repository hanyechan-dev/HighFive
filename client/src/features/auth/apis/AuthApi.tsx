import { api } from "../../../common/Axios";
import type { KakaoAccessTokenDto, LogInDto, MemberCreateDto, UserSignUpDto } from "../props/AuthProps";


export const loginApi = (loginDto : LogInDto) => {
  return api(false).post('/auth/token', loginDto);
};

export const SignUpApi = (signUpDto : UserSignUpDto) => {
  return api(false).post('/users', signUpDto);
};

export const kakaoLoginApi = (kakaoAccessTokenDto : KakaoAccessTokenDto) => {
  return api(false).post('/auth/kakao', kakaoAccessTokenDto);
};

export const nicknameInputApi = (nicknameInputDto : MemberCreateDto) => {
  return api(true).post('/members', nicknameInputDto);
};
  