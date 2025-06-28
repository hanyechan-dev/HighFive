import { api } from "../../../common/Axios";

export const createRequestApi = (
    targetJob: string,
    targetCompanyName: string,
    consultingType: string,
    coverLetterId: number,
    careerDescriptionId: number
) => {
    return api(true).post('/requests',
        {
            targetJob,
            targetCompanyName,
            consultingType,
            coverLetterId,
            careerDescriptionId

        })
}

export const readRequestsApi = (
    page: number,
    size: number,
    type: string
) => {

    if (type === '피드백') {
        return api(true).get('/requests/feedbacks', {
            params: {
                page,
                size,
            }
        });
    }
    else {
        return api(true).get('/requests/edits', {
            params: {
                page,
                size,
            }
        });
    }
}


export const readRequestApi = (
    id: number,

) => {
    return api(true).post('/requests/detail', {
        id
    });
}

export const readCompletedRequestApi = (
    id: number,


) => {
    return api(true).post('/requests/completions/detail', {
        id
    });
}

export const createConsultantRequestApi = (
    id: number,
) => {
    return api(true).put('/requests', {
        id
    });
}



