import { api } from "../../common/Axios.tsx";

export const JobPostingFilterApi = (
  careerType: string,
  educationLevel: string,
  workLocation: string,
  job: string,
  salary: string

) => {
  return api(true).post('/members/jobPostings', {
    careerType,
    educationLevel,
    workLocation,
    job,
    salary: Number(salary)
  });
};
