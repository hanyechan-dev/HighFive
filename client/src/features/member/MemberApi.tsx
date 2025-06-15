import { api } from "../../common/Axios.tsx";

export const nicknameInputApi = (
    nickname: string

) => {
    return api(true).post('/members', {
        nickname,
    });
};

export const readEducationsApi = () => {
    return api(true).get('/educations');
}

export const readCareersApi = () => {
    return api(true).get('/careers');
}

export const readCertificationsApi = () => {
    return api(true).get('/certifications');
}

export const readLanguageTestsApi = () => {
    return api(true).get('/language-tests');
}

export const readCareerDescriptionsApi = () => {
    return api(true).get('/career-descriptions');
}


export const readCoverLettersApi = () => {
    return api(true).get('/cover-letters');
}

