import { api } from "../../common/Axios";

export const consultingRequest = (page: number, size:number) => {
    return api(true).get('/ai-consultings', {
        params: {
            page,
            size
        }
    });
}

export const consultingApprve = (id: number) => {
    return api(true).post('/ai-consultings/approval', { id: id });
}

export const listClick = (id: number) => {
    return api(true).post('/ai-consultings/feedbacks/detail', { id: id });
}