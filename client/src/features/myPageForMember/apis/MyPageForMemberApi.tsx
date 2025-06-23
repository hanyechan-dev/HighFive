import { api } from "../../../common/Axios";
import type { CareerCreateDto, CareerDescriptionCreateDto, CareerDescriptionUpdateDto, CareerUpdateDto, CertificationCreateDto, CertificationUpdateDto, CoverLetterCreateDto, CoverLetterUpdateDto, EducationCreateDto, EducationUpdateDto, LanguageTestCreateDto, LanguageTestUpdateDto, MemberUpdateDto, MyPageUpdateDto, PasswordUpdateDto, ProposalUpdateDto } from "../props/myPageForMemberProps";

export const readMyPageApi = () => {
    return api(true).get('/members');
}

export const updateMyNickNameApi = (memberUpdateDto: MemberUpdateDto) => {
    return api(true).put('/members', memberUpdateDto);
}

export const updateMyPageApi = (myPageUpdateDto: MyPageUpdateDto) => {
    return api(true).put('/users/me', myPageUpdateDto);
}

export const updatePasswordApi = (passwordUpdateDto: PasswordUpdateDto) => {
    return api(true).put('/users/me/password', passwordUpdateDto);
}


export const deactivateAccountApi = (id: number) => {
    return api(true).put('/users/me/deactivation', { id });
}

export const readEducationsApi = () => {
    const instance = api(true);
    console.log("헤더 확인:", instance.defaults.headers); // Authorization 붙었는지 확인
    return instance.get('/educations'); // instance 재사용해야 토큰도 동일하게 적용됨
}

export const createEducationApi = (educationCreateDto: EducationCreateDto) => {
    return api(true).post('/educations', educationCreateDto);
}

export const updateEducationApi = (educationUpdateDto: EducationUpdateDto) => {
    return api(true).put('/educations', educationUpdateDto);
}

export const deleteEducationApi = (id: number) => {
    return api(true).post('/educations/delete', {
        id
    });
}

export const readCareersApi = () => {
    return api(true).get('/careers');
}


export const createCareerApi = (careerCreateDto: CareerCreateDto) => {
    return api(true).post('/careers', careerCreateDto);
}

export const updateCareerApi = (careerUpdateDto: CareerUpdateDto) => {
    return api(true).put('/careers', careerUpdateDto);
}

export const deleteCareerApi = (id: number) => {
    return api(true).post('/careers/delete', {
        id
    });
}

export const readCertificationsApi = () => {
    return api(true).get('/certifications');
}

export const createCertificationApi = (certificationCreateDto: CertificationCreateDto) => {
    return api(true).post('/certifications', certificationCreateDto);
}

export const updateCertificationApi = (certificationUpdateDto: CertificationUpdateDto) => {
    return api(true).put('/certifications', certificationUpdateDto);
}

export const deleteCertificationApi = (id: number) => {
    return api(true).post('/certifications/delete', {
        id
    });
}


export const readLanguageTestsApi = () => {
    return api(true).get('/language-tests');
}

export const createLanguageTestApi = (languageTestCreateDto: LanguageTestCreateDto) => {
    return api(true).post('/language-tests', languageTestCreateDto);
}

export const updateLanguageTestApi = (languageTestUpdateDto: LanguageTestUpdateDto) => {
    return api(true).put('/language-tests', languageTestUpdateDto);
}

export const deleteLanguageTestApi = (id: number) => {
    return api(true).post('/language-tests/delete', {
        id
    });
}

export const readCareerDescriptionsApi = () => {
    return api(true).get('/career-descriptions');
}


export const readCareerDescriptionApi = (id: number) => {
    return api(true).post('/career-descriptions/detail', {
        id
    });
}

export const createCareerDescriptionApi = (careerDescriptionCreateDto: CareerDescriptionCreateDto) => {
    return api(true).post('/career-descriptions', careerDescriptionCreateDto);
}

export const updateCareerDescriptionApi = (careerDescriptionUpdateDto: CareerDescriptionUpdateDto) => {
    return api(true).put('/career-descriptions', careerDescriptionUpdateDto);
}

export const deleteCareerDescriptionApi = (id: number) => {
    return api(true).post('/career-descriptions/delete', {
        id
    });
}

export const readCoverLettersApi = () => {
    return api(true).get('/cover-letters');
}

export const readCoverLetterApi = (id: number) => {
    return api(true).post('/cover-letters/detail', {
        id
    });
}

export const createCoverLetterApi = (coverLetterCreateDto: CoverLetterCreateDto) => {
    return api(true).post('/cover-letters', coverLetterCreateDto);
}

export const updateCoverLetterApi = (coverLetterUpdateDto: CoverLetterUpdateDto) => {
    return api(true).put('/cover-letters', coverLetterUpdateDto);
}

export const deleteCoverLetterApi = (id: number) => {
    return api(true).post('/cover-letters/delete', {
        id
    });
}


export const readMyProposalsApi = (page: number, size: number) => {
    return api(true).get('/members/proposals', {
        params: {
            page,
            size,
        }
    });
}


export const readMyProposalApi = (id: number) => {
    return api(true).post('/members/proposals/detail', {
        id,
    });
}

export const updateProposalApi = (proposalUpdateDto: ProposalUpdateDto) => {
    return api(true).put('/members/proposals', proposalUpdateDto);
}

export const readMyApplicationsApi = (page: number, size: number) => {
    return api(true).get('/members/applications', {
        params: {
            page,
            size,
        }
    });
}

export const readMyApplicationApi = (id: number) => {
    return api(true).post('/members/applications/detail', {
        id,
    });
}

export const readMyPaymentsApi = (page: number, size: number) => {
    return api(true).get("/payments", {
        params: {
            page,
            size,
        }
    });
}


