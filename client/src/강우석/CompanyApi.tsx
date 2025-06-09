import { api, apiForm } from "../common/Axios.tsx";


export const companyInfoInputModalApi = (
    companyName: string,
    businessNumber: string,
    representativeName: string,
    companyAddress: string,
    companyPhone: string, 
    industry: string,
    employeeCount: string, 
    establishedDate: string, 
    introduction: string,
    logoImage: File 
) => {
    const formData = new FormData();
    formData.append("companyName", companyName);
    formData.append("businessNumber", businessNumber);
    formData.append("representativeName", representativeName);
    formData.append("companyAddress", companyAddress);
    formData.append("companyPhone", companyPhone);
    formData.append("industry", industry);
    formData.append("employeeCount", employeeCount);
    formData.append("establishedDate", establishedDate);
    formData.append("introduction", introduction);
    formData.append("logoImage", logoImage);

    if (logoImage) { 
        formData.append('logoImage', logoImage);
    }
    return apiForm(true).post('/companies', formData);
};