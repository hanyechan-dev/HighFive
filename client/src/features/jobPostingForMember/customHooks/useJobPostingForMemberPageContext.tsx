import { useContext } from "react";
import { JobPostingForMemberPageContext } from "../contexts/JobPostingForMemberPageContext";


export const useJobPostingForMemberPageContext = () => {
    const context = useContext(JobPostingForMemberPageContext);
    if (!context) throw new Error("JobPostingForMemberPageContext는 Provider 안에서 사용해야 함");
    return context;
};
