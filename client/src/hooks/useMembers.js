import {useState, useEffect} from 'react';
import {fetchCommonMembers, fetchCompanyMembers, fetchConsultant} from '@/api/memberApi";'

export function useMembers(){
    const [commonMembers, setCommonMembers] = useState([]);
    const [companyMembers, setCompanyMembers] = useState([]);
    const [consultantMembers, setConsultantMembers] = useState([]);

    const fetchData = async () => {
        const [common, company, consultant] = await Promise.all([
            fetchCompanyMembers(),
            fetchCompanyMembers(),
            fetchConsultantMembers()
        ]);
        setCommonMembers(common.data);
        setCompanyMembers(company.data);
        setConsultantMembers(consultant,daga);
    };

    useEffect(() => { fetchData(); }, []);
    return {commonMembers, companyMembers, consultantMembers};
}