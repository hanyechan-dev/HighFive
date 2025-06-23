import { api } from "../../../common/Axios";

export const createRequestApi = (
    targetJob: string,
    targetCompanyName: string,
    type: string,
    coverLetterId: number,
    careerDescriptionId: number
) => {
    return api(true).post('/requests',
        {
            targetJob,
            targetCompanyName,
            type,
            coverLetterId,
            careerDescriptionId

        })
}

export const readRequestsApi = (
    page: number,
    size: number,
    type: string
) => {
    console.log("분기 직전")

    if (type === '피드백') {
        console.log("피드백 분기 직후")
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
    const instance = api(true);
    console.log("헤더 확인:", instance.defaults.headers); // Authorization 붙었는지 확인
    return instance.post('/requests/detail', {
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



