export interface aiConsultingDetailProps {
    targetCompanyName: string;
    targetJob: string;
    requestedDate: string;
    resume: string;
    careerDescription: string;
    coverLetter: string;
    aiContents: aiContentProps[];
}

export interface aiContentProps {
	item: string;
	content: string;
}


export interface aiContentsProps {
	aiContents: aiContentProps[];
}



export interface careerDescriptionContentProps {
	id: number;
	item: string;
	content: string;
}	


export interface careerDescriptionProps {
	id: number;
	title: string;
	contents: careerDescriptionContentProps[];
}

export interface coverLetterContentProps {
	id: number;
	item: string;
	content: string;
}


export interface coverLetterProps {
	id: number;
	title: string;
	contents: coverLetterContentProps[];
}


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
    educations : educationProps[];
    careers : careerProps[];
    certifications : certificationProps[];
    languageTests : languageTestProps[];
}

export interface careerDescriptionOrCoverLetterSummaryProps {
    id: number;
    title: string;
    createdDate: string;
}

export interface requestSummaryProps {
    id: number;
    targetJob: string;
    targetCompanyName: string;
    requestStatus: string;
    createdDate: string;
}
