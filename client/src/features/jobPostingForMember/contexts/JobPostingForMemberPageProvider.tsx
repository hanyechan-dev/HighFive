import { useReducer, type ReactNode } from "react";
import { JobPostingForMemberPageContext } from "./JobPostingForMemberPageContext";
import { initialState, reducer } from "./JobPostingForMemberPageReducer";

export const JobPostingForMemberPageProvider = ({ children }: { children: ReactNode }) => {
    const [state, dispatch] = useReducer(reducer, initialState);
    return (
        <JobPostingForMemberPageContext.Provider value={{ state, dispatch }}>
            {children}
        </JobPostingForMemberPageContext.Provider>
    );
};
