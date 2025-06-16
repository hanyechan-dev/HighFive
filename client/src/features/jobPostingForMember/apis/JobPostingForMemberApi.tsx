import { api } from "../../../common/Axios";
import { store } from "../../../common/store/store";


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
    return api(false).post('/members/job-postings/detail', {
        id
    }
    );
};

export const CreateApplicationApi = (jobPostingId: number, coverLetterId: number, careerDescriptionId: number) => {
    return api(true).post('members/applications', {
        jobPostingId,
        coverLetterId,
        careerDescriptionId
    }
    );
}

