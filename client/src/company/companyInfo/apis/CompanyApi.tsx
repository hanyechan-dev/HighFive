import { apiForm } from "../../../common/Axios.tsx";
import { api } from "../../../common/Axios.tsx";
import type { CompanyInfoResponse } from "../props/CompanyProps.tsx";

// 기업정보 조회 API
export const getCompanyInfoApi = () => {
  return api(true).get<CompanyInfoResponse>('/companies');
};

// 기업정보 생성 API
export const companyInfoInputModalApi = (
    companyName: string,
    businessNumber: string,
    representativeName: string,
    type: string,
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
    formData.append("type", type);
    formData.append("employeeCount", employeeCount);
    formData.append("establishedDate", establishedDate);
    if (logoImageFile) {
        formData.append("logoImageFile", logoImageFile);
    }
    return apiForm(true).post('/companies', formData);
};

// 기업정보 수정 API
export const updateCompanyInfoApi = (
    companyName: string,
    businessNumber: string,
    representativeName: string,
    type: string,
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
    formData.append("type", type);
    formData.append("employeeCount", employeeCount);
    formData.append("establishedDate", establishedDate);
    if (logoImageFile) {
        formData.append("logoImageFile", logoImageFile);
    }
    return apiForm(true).put('/companies', formData);
};

