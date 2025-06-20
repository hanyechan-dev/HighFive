import { api } from "../../../common/Axios"

export const memberPageClick = (page : number, size : number) => {
    return api(true).get('/admin/members',{
        params : {
            page,
            size
        }
});
} 

export const memberDetailClick = (id : number) =>{
    return api(true).post('/admin/members/detail',  {id : id});
}

export const companyPageClick = (page : number, size : number) => {
    return api(true).get('/admin/companies',{
         params : {
            page,
            size
        }
    });
}

export const companyDetailClick = (id : number) => {
    return api(true).post('/admin/companies/detail' , {id : id});
}

export const consultantPageClick = (page : number, size : number) => {
    return api(true).get('/admin/consultants',{
         params : {
            page,
            size
        }
    });
}

export const consultantDetailClick = (id : number) => {
    return api(true).post('admin/consultants/detail', {id : id});
}



export const companysApproveButtonClick = (page : number, size : number) => {
    return api(true).get('/admin/users/companies',{
        params: {
            page,
            size
        }
    });
}

export const consultantsApproveButtonClick = (page : number, size : number) => {
    return api(true).get('/admin/users/consultants',{
        params: {
            page,
            size
        }
    });
}

export const companyAndConsultantApprovalClick = (ids : number[]) => {
    const body = ids.map(id => ({id}));
    return api(true).put('/admin/users/approval', body)
}

export const companyAndConsultantRejectionClick = (ids : number[]) => {
    const body = ids.map(id => ({id}));
    return api(true).put('/admin/users/rejection', body)
}





