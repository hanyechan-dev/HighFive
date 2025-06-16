import { createContext } from "react";

import type { JobPostingForMemberPageAction, JobPostingForMemberPageState } from "./JobPostingForMemberPageTypes";

export const JobPostingForMemberPageContext = createContext<{
    state: JobPostingForMemberPageState;
    dispatch: React.Dispatch<JobPostingForMemberPageAction>;
} | undefined>(undefined);
