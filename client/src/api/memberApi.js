import axios from 'axios';
export const fetchCommonMembers = () => axios.get('api/members/common');
export const fetchCompanyMembers = () => axios.get('api/members/company');
export const fetchConsultantMembers = () => axios.get('/api/members.consultant');