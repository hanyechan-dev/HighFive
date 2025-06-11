export interface educationProps {
    id: number;
    schoolName: string;
    educationLevel: string;
    major: string;
    gpa: number;
    location: string;
    enterDate: string;
    graduateDate: string;
}

export interface careerProps {
    id: number;
    companyName: string;
    job: string;
    department: string;
    position: string;
    startDate: string;
    endDate: string;
}

export interface certificationProps {
    id: number;
    certificationName: string;
    issuingOrg: string;
    grade: string;
    score: string;
    certificationNo: string;
    acquisitionDate: string;

}

export interface languageTestProps {
    id: number;
    languageType: string;
    testName: string;
    issuingOrg: string;
    grade: string;
    score: string;
    certificationNo: string;
    acquisitionDate: string;
}



export interface resumeProps {
    education : educationProps[];
    career : careerProps[];
    certification : certificationProps[];
    languageTest : languageTestProps[];
}