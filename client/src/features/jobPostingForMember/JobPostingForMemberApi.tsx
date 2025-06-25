import { api } from "../../common/Axios.tsx";
import { store } from "../../common/store/store.ts";

export const JobPostingListForMemberApi = (
    page: number,
) => {
    const { careerType,
        educationLevel,
        workLocation,
        job,
        salary } = store.getState().jobPostingFilter.filter;


    return api(true).post('/members/jobPostings', {
        careerType,
        educationLevel,
        workLocation,
        job,
        salary: Number(salary)
    },
        {
            params: {
                page,
                size: 10
            },
        }
    );
};

export const JobPostingMainCardForMemberApi = () => {
    return api(true).get('/members/jobPostings/mains');
    
};

export const JobPostingUnderCardForMemberApi = () => {
    return api(true).get('/members/jobPostings/unders');
    
};

export const JobPostingDetailApi = (id:number) =>{
    return api(false).post('/members/jobPostings/detail',{
        id
    }
);
};

