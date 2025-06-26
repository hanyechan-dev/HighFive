import { api, apiForm } from "../../../common/Axios";
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
  

export const companyInfoInputApi = (
    companyName: string,
    businessNumber: string,
    representativeName: string,
    companyType: string,
    companyAddress: string,
    companyPhone: string,
    industry: string,
    employeeCount: string,
    establishedDate: string,
    introduction: string,
    logoImageFile: File | null
) => {
    const formData = new FormData();
    formData.append("companyName", companyName);
    formData.append("industry", industry);
    formData.append("representativeName", representativeName);
    formData.append("businessNumber", businessNumber);
    formData.append("companyAddress", companyAddress);
    formData.append("companyPhone", companyPhone);
    formData.append("introduction", introduction);
    formData.append("companyType", companyType);
    formData.append("employeeCount", employeeCount);
    formData.append("establishedDate", establishedDate);
    if (logoImageFile) {
        formData.append("logoImageFile", logoImageFile);
    }
    return apiForm(true).post('/companies', formData);
};