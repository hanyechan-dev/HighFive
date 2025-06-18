import { apiForm } from "../../common/Axios.tsx";


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

