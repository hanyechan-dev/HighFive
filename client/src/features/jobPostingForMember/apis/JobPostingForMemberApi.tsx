import { api } from "../../../common/Axios";
import { store } from "../../../common/store/store";
import type { ApplicationCreateDto } from "../props/JobPostingForMemberProps";


export const JobPostingListForMemberApi = (
    page: number,
    size: number
) => {
    const { careerType,
        educationLevel,
        workLocation,
        job,
        salary } = store.getState().jobPostingFilter.filter;


    return api(true).post('/members/job-postings', {
        careerType,
        educationLevel,
        workLocation,
        job,
        salary: Number(salary)
    },
        {
            params: {
                page,
                size
            },
        }
    );
};

export const JobPostingMainCardForMemberApi = () => {
    return api(true).get('/members/job-postings/mains');

};

export const JobPostingUnderCardForMemberApi = () => {
    return api(true).get('/members/job-postings/unders');

};

export const JobPostingDetailApi = (id: number) => {
    return api(true).post('/members/job-postings/detail', {
        id
    }
    );
};

export const CreateApplicationApi = (applicationCreateDto: ApplicationCreateDto) => {
    return api(true).post('/members/applications', applicationCreateDto
    );
}

export const ReadMyCoverLetterApi = (id : number) =>{
    return api(true).post('/cover-letters/detail', {id});
}

export const ReadMyCareerDescriptionApi = (id : number) =>{
    return api(true).post('/career-descriptions/detail', {id});
}

