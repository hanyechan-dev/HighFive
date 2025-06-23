import type { CareerCreateDto, CareerUpdateDto, CertificationCreateDto, CertificationUpdateDto, EducationCreateDto, EducationUpdateDto, LanguageTestCreateDto, LanguageTestUpdateDto } from "../../features/myPageForMember/props/myPageForMemberProps";

export const validateEducation = (
    education: EducationCreateDto | EducationUpdateDto
): string | null => {
    const today = new Date();
    const enter = new Date(education.enterDate);
    const graduate = education.graduateDate ? new Date(education.graduateDate) : null;
    const gpaNumber = parseFloat(education.gpa);

    if (!education.schoolName || !education.educationLevel || !education.major || !education.gpa || !education.location || !education.enterDate) {
        return "모든 필수 정보를 입력해주세요.";
    }
    if (education.schoolName.length > 20) return "학교명은 20자 이하로 입력해야합니다.";
    if (education.educationLevel === "") return "학위는 필수로 입력해야합니다.";
    if (education.major.length > 20) return "전공명은 20자 이하로 입력해야합니다.";
    if (isNaN(gpaNumber) || gpaNumber < 0 || gpaNumber > 4.5) return "학점은 0.0 이상 4.5 이하로 입력해야합니다.";
    if (education.location.length > 10) return "지역은 10자 이하로 입력해야합니다.";
    if (!education.enterDate) return "입학일은 필수로 입력해야합니다.";
    if (enter > today) return "입학일은 과거 날짜여야 합니다.";
    if (graduate && graduate < enter) return "졸업일은 입학일보다 빠를 수 없습니다.";
    if (graduate && graduate > today) return "졸업일은 과거 날짜여야 합니다.";

    return null; // 이상 없음
};

export const validateCareer = (career: CareerCreateDto | CareerUpdateDto): string | null => {
    const today = new Date();
    const startDate = new Date(career.startDate);
    const endDate = career.endDate ? new Date(career.endDate) : null;

    if (!career.companyName || !career.job || !career.department || !career.position || !career.startDate) {
        return "모든 필수 정보를 입력해주세요.";
    }
    if (career.companyName.length > 20) return "기업명은 20자 이하로 입력해야합니다.";
    if (career.job.length > 20) return "직무명은 20자 이하로 입력해야합니다.";
    if (career.department.length > 10) return "부서명은 10자 이하로 입력해야합니다.";
    if (career.position.length > 10) return "직급명은 10자 이하로 입력해야합니다.";
    if (!career.startDate) return "입사일은 필수로 입력해야합니다.";
    if (startDate > today) return "입사일은 과거 날짜여야 합니다.";
    if (endDate && endDate < startDate) return "퇴사일은 입사일보다 빠를 수 없습니다.";
    if (endDate && endDate > today) return "퇴사일은 과거 날짜여야 합니다.";

    return null; // 이상 없음
};



export const validateCertification = (
    certification: CertificationCreateDto | CertificationUpdateDto
): string | null => {
    const today = new Date();
    const acquisitionDate = new Date(certification.acquisitionDate);

    if (
        !certification.certificationName ||
        !certification.issuingOrg ||
        !certification.certificationNo ||
        !certification.acquisitionDate
    ) {
        return "모든 필수 정보를 입력해주세요.";
    }

    if (certification.certificationName.length > 20)
        return "자격증명은 20자 이하로 입력해야합니다.";

    if (certification.issuingOrg.length > 20)
        return "발행처는 20자 이하로 입력해야합니다.";

    if (!certification.grade && !certification.score)
        return "등급 또는 점수는 필수로 입력해야합니다.";

    if (certification.grade && certification.grade.length > 10)
        return "등급은 10자 이하로 입력해야합니다.";

    if (certification.score && certification.score.length > 10)
        return "점수는 10자 이하로 입력해야합니다.";

    if (certification.certificationNo.length > 20)
        return "인증번호는 20자 이하로 입력해야합니다.";

    if (acquisitionDate > today)
        return "취득일은 과거 날짜여야 합니다.";

    return null; // 이상 없음
};

export const validateLanguageTest =(languageTest:LanguageTestCreateDto | LanguageTestUpdateDto)=>{
    const today = new Date();
    const acquisitionDate = new Date(languageTest.acquisitionDate);

    if(!languageTest.languageType ||
        !languageTest.testName ||
        !languageTest.issuingOrg ||
        !languageTest.certificationNo ||
        !languageTest.acquisitionDate){
        return "모든 필수 정보를 입력해주세요.";
    }
    if(languageTest.languageType.length > 10)
        return "언어는 10자 이하로 입력해야합니다.";
    if(languageTest.testName.length > 20)
        return "시험명은 20자 이하로 입력해야합니다.";
    if(languageTest.issuingOrg.length > 20)
        return "발행처는 20자 이하로 입력해야합니다.";
    if (!languageTest.grade && !languageTest.score)
        return "등급 또는 점수는 필수로 입력해야합니다.";
    if(languageTest.grade && languageTest.grade.length > 10)
        return "등급은 10자 이하로 입력해야합니다.";
    if(languageTest.score && languageTest.score.length > 10)
        return "점수는 10자 이하로 입력해야합니다.";
    if(languageTest.certificationNo.length > 20)
        return "인증번호는 20자 이하로 입력해야합니다.";
    if(acquisitionDate > today)
        return "취득일은 과거 날짜여야 합니다.";
    return null; // 이상 없음
}

export const validateLogin = (email:string,password:string)=>{
    if(!email || !password){
        return "모든 필수 정보를 입력해주세요.";
    }
    if(email.length > 20){
        return "이메일은 20자 이하로 입력해야합니다.";
    }
    if(password.length > 20){
        return "비밀번호는 20자 이하로 입력해야합니다.";
    }
    return null; // 이상 없음
}